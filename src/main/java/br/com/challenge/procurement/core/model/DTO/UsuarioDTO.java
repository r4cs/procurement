package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.RoleEnum;
import br.com.challenge.procurement.core.model.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDTO(
        Long id,
        @NotBlank
        @Size(max=255)
        String nome,
        @NotBlank
        @Size(max=255)
        String sobrenome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        RoleEnum role
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}
