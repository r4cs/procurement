package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.service.PropostaDeVendaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/proposta-fornecedor")
public class PropostaDeVendaController {

    private final PropostaDeVendaService propostaDeVendaService;

    public PropostaDeVendaController(PropostaDeVendaService propostaDeVendaService) {
        this.propostaDeVendaService = propostaDeVendaService;
    }

    @PostMapping
    public ResponseEntity<PropostaDeVenda> cadastrar(@Valid @RequestBody PropostaDeVendaDTO dto) {
        System.out.println("Dados de proposta de venda:" +dto);
        return ResponseEntity.ok(propostaDeVendaService.criar(dto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> obterPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(propostaDeVendaService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PropostaDeVenda>> listarTodos(Pageable pageable) {
        Pageable defaultPageable = PageRequest.of(
                pageable.getPageNumber(),
                10,
                Sort.by("id")
        );

        Page<PropostaDeVenda> propostaDeVendas = propostaDeVendaService.listar(pageable);
        return ResponseEntity.ok(propostaDeVendas);
    }

    @Transactional
    @PatchMapping(value = "{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> atualizarPropostaDeVenda(@PathVariable Long id, @Valid PropostaDeVenda novaPropostaDeVenda) {
        return ResponseEntity.ok(propostaDeVendaService.update(id, novaPropostaDeVenda));
    }

    @DeleteMapping(value = "id")
    public ResponseEntity<String>  deletarPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(propostaDeVendaService.delete(id));
    }
}
