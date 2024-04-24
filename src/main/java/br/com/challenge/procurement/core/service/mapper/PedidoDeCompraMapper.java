package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoDeCompraMapper {
    PedidoDeCompraMapper INSTANCE = Mappers.getMapper(PedidoDeCompraMapper.class);

    PedidoDeCompraDTO entityToDto(PedidoDeCompra pedidoDeCompra);

    PedidoDeCompra dtoToEntity(PedidoDeCompraDTO dto);
}
