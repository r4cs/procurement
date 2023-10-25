package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.model.SolicitacaoDeCompra;
import br.com.challenge.procurement.service.SolicitacaoDeCompraService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    @Transactional
    public void cadastrar(@RequestBody @Valid SolicitacaoDeCompraDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        solicitacaoDeCompraService.create(dto);
    }

    @GetMapping
    public List<SolicitacaoDeCompra> listarTodos() {
        return solicitacaoDeCompraService.list();
    }

    @GetMapping(value = "/{id}")
    public Optional<SolicitacaoDeCompra> obterSolicitacaoDeCompra(@PathVariable Long id){
        return solicitacaoDeCompraService.getSolicitacaoDeCompraById(id);
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarSolicitacaoDeCompra(@PathVariable Long id, @RequestBody @Valid SolicitacaoDeCompra novaSolicitacao) {
        solicitacaoDeCompraService.update(id, novaSolicitacao);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSolicitacaoDeCompra(@PathVariable Long id) {
        solicitacaoDeCompraService.delete(id);
    }

}
