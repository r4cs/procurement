package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.model.entities.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {
    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    ProdutoDTO entityToDto(Produto produto);

    Produto dtoToEntity(ProdutoDTO dto);
}
