package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.service.PropostaDeVendaService;
import io.swagger.v3.oas.annotations.Operation;
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
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> obterPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PropostaDeVenda>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<PropostaDeVenda> proposta = service.list(defaultPageable);
        return ResponseEntity.ok(proposta);
    }


    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<PropostaDeVenda>> getAll() { return ResponseEntity.ok(service.listAll());}



    @PatchMapping(value = "{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> atualizarPropostaDeVenda(@PathVariable Long id, @Valid PropostaDeVenda novaPropostaDeVenda) {
        return ResponseEntity.ok(service.update(id, novaPropostaDeVenda));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String>  deletarPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
