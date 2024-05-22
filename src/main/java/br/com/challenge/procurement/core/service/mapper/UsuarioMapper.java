package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;

public class UsuarioMapper {

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.id());
        usuario.setNome(dto.nome());
        usuario.setSenha(dto.senha());
        usuario.setEmail(dto.email());
        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(usuario);
    }
}
