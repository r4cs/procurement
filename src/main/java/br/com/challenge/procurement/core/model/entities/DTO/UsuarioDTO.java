package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        String senha,
        @NotBlank
        @Email
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getEmail()
        );
    }
}
