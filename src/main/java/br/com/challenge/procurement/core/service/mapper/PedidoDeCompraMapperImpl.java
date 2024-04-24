package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import org.springframework.stereotype.Component;

@Component
public class PedidoDeCompraMapperImpl implements  PedidoDeCompraMapper {

    @Override
    public PedidoDeCompraDTO entityToDto(PedidoDeCompra pedidoDeCompra) {
        if (pedidoDeCompra == null) {
            return null;
        }

        return new PedidoDeCompraDTO(
                pedidoDeCompra.getSolicitacao(),
                pedidoDeCompra.getTipoDePagamento(),
                pedidoDeCompra.getData_pedido()
        );
    }

    @Override
    public PedidoDeCompra dtoToEntity(PedidoDeCompraDTO dto) {
        if (dto == null) {
            return null;
        }

        return new PedidoDeCompra(dto);
    }
}
