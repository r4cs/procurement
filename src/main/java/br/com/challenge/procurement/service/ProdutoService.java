package br.com.challenge.procurement.service;

import br.com.challenge.procurement.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.model.Produto;
import br.com.challenge.procurement.repositories.ProdutoRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepo produtoRepo;

    public ProdutoService(ProdutoRepo produtoRepo) {
        this.produtoRepo=produtoRepo;
    }
    public void create(ProdutoDTO dto) {
        Produto produto = new Produto(dto);
        produtoRepo.save(produto);
    }

    public List<Produto> list() {
        return produtoRepo.findAll();
    }

    public Optional<Produto> getProdutoById(String sku) {
        return produtoRepo.findById(sku);
    }

    public Produto update(String sku, Produto updatedProduto) {
        Optional<Produto> prodAntigo = produtoRepo.findById(sku);

        if (prodAntigo.isPresent()) {
            Produto produto = prodAntigo.get();

            produto.setNome_produto(updatedProduto.getNome_produto());
            produto.setQtde(updatedProduto.getQtde());

            return produtoRepo.save(produto);
        } else {
            System.out.println("criar classe ProdutoNotFoundException");
            return null;
        }
    }

    public void delete(String sku) {
        produtoRepo.deleteById(sku);
    }
}









