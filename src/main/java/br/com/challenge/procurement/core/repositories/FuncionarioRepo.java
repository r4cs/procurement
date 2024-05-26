package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.models.entities.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Long> {

    @Override
    Page<Funcionario> findAll(Pageable pageable);

    Optional<Funcionario> findByEmail(String email);

}
