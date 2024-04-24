package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SolicitacaoDeCompraMapper {
    SolicitacaoDeCompraMapper INSTANCE = Mappers.getMapper(SolicitacaoDeCompraMapper.class);

    SolicitacaoDeCompraDTO entityToDto(SolicitacaoDeCompra solicitacaoDeCompra);

    SolicitacaoDeCompra dtoToEntity(SolicitacaoDeCompraDTO dto);
}
