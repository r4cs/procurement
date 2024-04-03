package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;

import java.time.LocalDateTime;

public record PedidoDeCompraDTO(
        SolicitacaoDeCompra solicitacao_id,
        LocalDateTime data_pedido
        )
{
    public PedidoDeCompraDTO(PedidoDeCompra pedidoDeCompra) {
        this(
                pedidoDeCompra.getSolicitacao(),
                pedidoDeCompra.getData_pedido()
        );

    }


}
