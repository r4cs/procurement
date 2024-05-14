package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperImpl implements UsuarioMapper{

    @Override
    public UsuarioDTO entityToDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    @Override
    public Usuario dtoToEntity(UsuarioDTO dto) {
        if (dto == null){
            return null;
        }
        return new Usuario(dto);
    }

}
