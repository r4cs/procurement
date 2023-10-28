package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepo extends JpaRepository<Fornecedor, Long> {
}
