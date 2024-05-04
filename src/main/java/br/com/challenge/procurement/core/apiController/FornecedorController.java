package br.com.challenge.procurement.core.apiController;


import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.service.FornecedorService;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<String> cadastrar(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        System.out.println("dados fornecedor: " + fornecedorDTO);
        return ResponseEntity.ok(fornecedorService.criar(fornecedorDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, razao_social, cnpj, nome_contato, telefone, email, endereco")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {

        Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));

        Page<Fornecedor> fornecedores = fornecedorService.listaFornecedores(defaultPageable);
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Fornecedor>> obterFornecedor(@PathVariable Long id){
        return ResponseEntity.ok(fornecedorService.getFornecedorById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor novoFornecedor) {
        return ResponseEntity.ok(fornecedorService.updateFornecedor(id, novoFornecedor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.deleteFornecedor(id));
    }
}
