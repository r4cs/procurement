package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.service.SolicitacaoDeCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/api/solicitacao")
public class SolicitacaoDeCompraController {

    private final SolicitacaoDeCompraService solicitacaoDeCompraService;

    @Autowired
    public SolicitacaoDeCompraController(SolicitacaoDeCompraService solicitacaoDeCompraService) {
        this.solicitacaoDeCompraService = solicitacaoDeCompraService;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDeCompra> cadastrar(@RequestBody @Valid SolicitacaoDeCompraDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        return ResponseEntity.ok(solicitacaoDeCompraService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<SolicitacaoDeCompra>> listarTodos(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<SolicitacaoDeCompra> solicitacoes = solicitacaoDeCompraService.list(defaultPageable);
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<SolicitacaoDeCompra>> obterSolicitacaoDeCompra(@PathVariable Long id){
        return ResponseEntity.ok(solicitacaoDeCompraService.getSolicitacaoDeCompraById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SolicitacaoDeCompra> atualizarSolicitacaoDeCompra(@PathVariable Long id, @RequestBody @Valid SolicitacaoDeCompra novaSolicitacao) {
        return ResponseEntity.ok(solicitacaoDeCompraService.update(id, novaSolicitacao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSolicitacaoDeCompra(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoDeCompraService.delete(id));
    }
}
