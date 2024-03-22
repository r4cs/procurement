package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Permissao;
import br.com.challenge.procurement.core.entities.Usuario;

public record UsuarioDTO(
        String nome,
        Permissao permissao,
        String senha,
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getNome(),
                usuario.getPermissao(),
                usuario.getSenha(),
                usuario.getEmail()
        );
    }
}
