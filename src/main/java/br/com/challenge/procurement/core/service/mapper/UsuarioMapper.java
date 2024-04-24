package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO entityToDto(Usuario usuario);

    Usuario dtoToEntity(UsuarioDTO dto);
}
