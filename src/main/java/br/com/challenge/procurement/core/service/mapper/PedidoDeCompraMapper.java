package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.entities.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.entities.PedidoDeCompra;

public class PedidoDeCompraMapper {
    public PedidoDeCompra toEntity(PedidoDeCompraDTO dto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra();
        pedidoDeCompra.setId(dto.id());
        pedidoDeCompra.setSolicitacao_id(dto.solicitacao_id());
        pedidoDeCompra.setFornecedor_id(dto.fornecedor_id());
        pedidoDeCompra.setData_entrega_prevista(dto.data_entrega_prevista());
        pedidoDeCompra.setData_pedido(dto.data_pedido());
        return pedidoDeCompra;
    }

    public PedidoDeCompraDTO toDTO(PedidoDeCompra pedidoDeCompra) {
        return new PedidoDeCompraDTO(pedidoDeCompra);
    }
}
