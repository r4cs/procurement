package br.com.challenge.procurement.repositories;

import br.com.challenge.procurement.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, String> {
}
