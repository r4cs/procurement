package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Usuario;

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
