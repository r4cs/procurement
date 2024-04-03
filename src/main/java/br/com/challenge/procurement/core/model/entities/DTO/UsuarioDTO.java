package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.Usuario;

public record UsuarioDTO(
        String nome,
        String senha,
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
