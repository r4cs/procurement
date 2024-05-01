package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.service.ProdutoService;
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
@RequestMapping(value = "/api/produto") // , produces = "application/json")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        System.out.println("dados: "+ dto);
        return ResponseEntity.ok(produtoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listarTodos(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<Produto> produtos = produtoService.list(defaultPageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Produto>> obterProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid Produto novoProduto) {
        return ResponseEntity.ok(produtoService.update(String.valueOf(id), novoProduto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.delete(String.valueOf(id)));
    }

}
