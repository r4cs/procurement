package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.model.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepo extends JpaRepository<Produto, String> {
    Page<Produto> findAll(Pageable pageable);
}
