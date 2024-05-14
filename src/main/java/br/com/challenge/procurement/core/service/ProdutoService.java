package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.repositories.ProdutoRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepo repo;

    public ProdutoService(ProdutoRepo repo) {
        this.repo=repo;
    }
    @Transactional
    public Produto create(ProdutoDTO dto) {
        Produto produto = new Produto(dto);
        return repo.save(produto);
    }

    public Page<Produto> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<Produto> listAll() {return repo.findAll();}

    public Optional<Produto> getProdutoById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Produto update(Long id, Produto updatedProduto) {
        Optional<Produto> prodAntigo = repo.findById(id);

        if (prodAntigo.isPresent()) {
            Produto produto = prodAntigo.get();

            produto.setNome_produto(updatedProduto.getNome_produto());

            return repo.save(produto);
        } else {
            System.out.println("criar classe ProdutoNotFoundException");
            return null;
        }
    }

    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Produto excluído";
    }
}
