package br.com.challenge.procurement.apiController;


import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.service.FornecedorService;
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
@RequestMapping(value="/api/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrar(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        System.out.println("dados fornecedor: " + fornecedorDTO);
        return ResponseEntity.ok(fornecedorService.criar(fornecedorDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listarTodos(Pageable pageable) {
        Pageable defaultPageable = PageRequest.of(
                pageable.getPageNumber(),
                10,
                Sort.by("id")
        );
        Page<Fornecedor> fornecedores = fornecedorService.listaFornecedores(defaultPageable);
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Fornecedor>> obterFornecedor(@PathVariable Long id){
        return ResponseEntity.ok(fornecedorService.getFornecedorById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor novoFornecedor) {
        return ResponseEntity.ok(fornecedorService.updateFornecedor(id, novoFornecedor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.deleteFornecedor(id));
    }
}
