package br.com.challenge.procurement.core.models.DTO;

import br.com.challenge.procurement.core.models.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.models.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.models.entities.TipoDePagamento;

import java.time.LocalDateTime;

public record PedidoDeCompraDTO(
        SolicitacaoDeCompra solicitacao_id,
        TipoDePagamento tipoDePagamento,
        LocalDateTime data_pedido
        )
{
    public PedidoDeCompraDTO(PedidoDeCompra pedidoDeCompra) {
        this(
                pedidoDeCompra.getSolicitacao(),
                pedidoDeCompra.getTipoDePagamento(),
                pedidoDeCompra.getData_pedido()
        );
    }
}
