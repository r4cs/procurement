package br.com.challenge.procurement.repositories;

import br.com.challenge.procurement.model.Fornecedor;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepo extends JpaRepository<Fornecedor, Long> {
}
