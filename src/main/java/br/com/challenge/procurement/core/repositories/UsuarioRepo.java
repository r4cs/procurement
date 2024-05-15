package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    @Override
    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findByEmail(String email);
}
