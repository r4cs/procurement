package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.model.Produto;
import br.com.challenge.procurement.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        System.out.println("dados: "+ dto);
        produtoService.create(dto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.list();
    }

    @GetMapping(value = "/{sku}")
    public Optional<Produto> obterProduto(@PathVariable String sku) {
        return produtoService.getProdutoById(sku);
    }

    @PatchMapping(value = "/{sku}")
    @Transactional
    public void atualizarProduto(@PathVariable String sku, @RequestBody @Valid Produto novoProduto) {
        produtoService.update(sku, novoProduto);
    }

    @DeleteMapping(value = "/{sku}")
    public void deletarProduto(@PathVariable String sku) {
        produtoService.delete(sku);
    }


















}
