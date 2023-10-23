package br.com.challenge.procurement.repositories;

import br.com.challenge.procurement.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
}
