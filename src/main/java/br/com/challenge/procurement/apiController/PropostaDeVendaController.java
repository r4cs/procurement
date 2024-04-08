package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.model.entities.Usuario;
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

//    @GetMapping
//    public ResponseEntity<Page<PropostaDeVenda>> listarTodos(Pageable pageable) {
//        Pageable defaultPageable = PageRequest.of(
//                pageable.getPageNumber(),
//                10,
//                Sort.by("id")
//        );
//
//        Page<PropostaDeVenda> propostaDeVendas = propostaDeVendaService.listar(pageable);
//        return ResponseEntity.ok(propostaDeVendas);
//    }
    @GetMapping
    public ResponseEntity<Page<PropostaDeVenda>> listarTodos(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<PropostaDeVenda> proposta = propostaDeVendaService.listar(defaultPageable);
        return ResponseEntity.ok(proposta);
    }
    @PatchMapping(value = "{id}")
    public ResponseEntity<Optional<PropostaDeVenda>> atualizarPropostaDeVenda(@PathVariable Long id, @Valid PropostaDeVenda novaPropostaDeVenda) {
        return ResponseEntity.ok(propostaDeVendaService.update(id, novaPropostaDeVenda));
    }

    @DeleteMapping(value = "id")
    public ResponseEntity<String>  deletarPropostaDeVenda(@PathVariable Long id) {
        return ResponseEntity.ok(propostaDeVendaService.delete(id));
    }
}
