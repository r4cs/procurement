package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.entities.Produto;
import br.com.challenge.procurement.core.repositories.ProdutoRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepo produtoRepo;

    public ProdutoService(ProdutoRepo produtoRepo) {
        this.produtoRepo=produtoRepo;
    }
    public Produto create(ProdutoDTO dto) {
        Produto produto = new Produto(dto);
        return produtoRepo.save(produto);
    }

    public Page<Produto> list(Pageable pageable) {
        return produtoRepo.findAll(pageable);
    }

    public Optional<Produto> getProdutoById(String sku) {
        return produtoRepo.findById(sku);
    }

    public Produto update(String sku, Produto updatedProduto) {
        Optional<Produto> prodAntigo = produtoRepo.findById(sku);

        if (prodAntigo.isPresent()) {
            Produto produto = prodAntigo.get();

            produto.setNome_produto(updatedProduto.getNome_produto());

            return produtoRepo.save(produto);
        } else {
            System.out.println("criar classe ProdutoNotFoundException");
            return null;
        }
    }

    public String delete(String sku) {
        produtoRepo.deleteById(sku);
        return "Produto excluído";
    }
}