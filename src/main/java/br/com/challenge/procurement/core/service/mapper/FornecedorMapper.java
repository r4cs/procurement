package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EnderecoMapper.class})
public interface FornecedorMapper {
    FornecedorMapper INSTANCE = Mappers.getMapper(FornecedorMapper.class);

    FornecedorDTO entityToDto(Fornecedor fornecedor);

    Fornecedor dtoToEntity(FornecedorDTO dto);
}
