package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.entities.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.entities.Produto;
import br.com.challenge.procurement.core.service.ProdutoService;
import jakarta.transaction.Transactional;
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

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        System.out.println("dados: "+ dto);
        return ResponseEntity.ok(produtoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listarTodos(Pageable pageable) {
        Pageable defaultPageable = PageRequest.of(
                pageable.getPageNumber(),
                5,
                Sort.by("id")
        );

        Page<Produto> produtos = produtoService.list(defaultPageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/{sku}")
    public ResponseEntity<Optional<Produto>> obterProduto(@PathVariable String sku) {
        return ResponseEntity.ok(produtoService.getProdutoById(sku));
    }

    @PatchMapping(value = "/{sku}")
    @Transactional
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String sku, @RequestBody @Valid Produto novoProduto) {
        return ResponseEntity.ok(produtoService.update(sku, novoProduto));
    }

    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<String> deletarProduto(@PathVariable String sku) {
        return ResponseEntity.ok(produtoService.delete(sku));
    }

}
