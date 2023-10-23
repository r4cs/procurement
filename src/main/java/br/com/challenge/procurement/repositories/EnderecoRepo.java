package br.com.challenge.procurement.repositories;

import br.com.challenge.procurement.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepo extends JpaRepository<Endereco, Long> { }
