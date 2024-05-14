package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.service.SolicitacaoDeCompraService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/solicitacao")
public class SolicitacaoDeCompraController {

    private final SolicitacaoDeCompraService service;

    @Autowired
    public SolicitacaoDeCompraController(SolicitacaoDeCompraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDeCompra> cadastrar(@RequestBody @Valid SolicitacaoDeCompraDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<SolicitacaoDeCompra>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<SolicitacaoDeCompra> solicitacoes = service.list(defaultPageable);
        return ResponseEntity.ok(solicitacoes);
    }


    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<SolicitacaoDeCompra>> getAll() { return ResponseEntity.ok(service.listAll());}


    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<SolicitacaoDeCompra>> obterSolicitacaoDeCompra(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<SolicitacaoDeCompra> atualizarSolicitacaoDeCompra(@PathVariable Long id, @RequestBody @Valid SolicitacaoDeCompra novaSolicitacao) {
        return ResponseEntity.ok(service.update(id, novaSolicitacao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarSolicitacaoDeCompra(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}