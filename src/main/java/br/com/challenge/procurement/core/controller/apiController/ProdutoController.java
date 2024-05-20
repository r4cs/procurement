package br.com.challenge.procurement.core.controller.apiController;

import br.com.challenge.procurement.core.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.service.ProdutoService;
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
@RequestMapping(value = "/api/produto") // , produces = "application/json")
public class ProdutoController {

    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        System.out.println("dados: "+ dto);
        return ResponseEntity.ok(service.create(dto));
    }


    @GetMapping
    public ResponseEntity<Page<Produto>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<Produto> produtos = service.list(defaultPageable);
        return ResponseEntity.ok(produtos);
    }


    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAll() { return ResponseEntity.ok(service.listAll());}


    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Produto>> obterProduto(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid Produto novoProduto) {
        return ResponseEntity.ok(service.update(String.valueOf(id), novoProduto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(String.valueOf(id)));
    }

}
