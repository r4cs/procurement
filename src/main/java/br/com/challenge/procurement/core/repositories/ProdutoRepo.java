package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, String> {
}
