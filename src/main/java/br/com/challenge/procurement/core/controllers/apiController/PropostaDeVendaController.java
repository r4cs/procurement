package br.com.challenge.procurement.core.controllers.apiController;

import br.com.challenge.procurement.core.models.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.models.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.services.PropostaDeVendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposta")
public class PropostaDeVendaController {

    private final PropostaDeVendaService service;

    public PropostaDeVendaController(PropostaDeVendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PropostaDeVenda> cadastrar(@Valid @RequestBody PropostaDeVendaDTO dto) {
        System.out.println("Dados de proposta de venda:" +dto);
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> obterPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PropostaDeVenda>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, pedido_compra, valor_unitario, valor_total")
            @RequestParam(required = false, defaultValue = "id") String orderBy
            ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by(orderBy)
        );
        Page<PropostaDeVenda> proposta = service.listar(defaultPageable);
        return ResponseEntity.ok(proposta);
    }


    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<PropostaDeVenda>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> atualizarPropostaDeVenda(@PathVariable Long id, @Valid PropostaDeVenda novaPropostaDeVenda) {
        return ResponseEntity.ok(service.update(id, novaPropostaDeVenda));
    }

    @DeleteMapping(value = "id")
    public ResponseEntity<String>  deletarPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}