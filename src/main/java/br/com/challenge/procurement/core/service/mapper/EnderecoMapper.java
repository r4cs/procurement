package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.model.entities.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO entityToDto(Endereco endereco);

    Endereco dtoToEntity(EnderecoDTO dto);
}

