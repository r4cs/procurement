package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
}
