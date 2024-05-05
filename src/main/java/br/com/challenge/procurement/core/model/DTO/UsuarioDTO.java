package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.Usuario;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @Null
        Long id,
        @NotBlank
        @Size(max=255)
        String nome,
        @NotBlank
        @Email
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
