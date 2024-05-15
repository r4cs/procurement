package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.Role;
import br.com.challenge.procurement.core.model.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDTO(
        @Null
        Long id,
        @NotBlank
        @Size(max=255)
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        List<Role> roles
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
        );
    }
}
