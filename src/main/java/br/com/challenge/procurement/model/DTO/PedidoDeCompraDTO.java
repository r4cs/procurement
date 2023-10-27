package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Fornecedor;
import br.com.challenge.procurement.model.PedidoDeCompra;
import br.com.challenge.procurement.model.SolicitacaoDeCompra;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PedidoDeCompraDTO(
        SolicitacaoDeCompra solicitacao_id,
        Fornecedor fornecedor_id,
        LocalDate data_entrega_prevista,
        LocalDateTime data_pedido
        )
{
    public PedidoDeCompraDTO(PedidoDeCompra pedidoDeCompra) {
        this(
                pedidoDeCompra.getSolicitacao(),
                pedidoDeCompra.getFornecedor(),
                pedidoDeCompra.getData_entrega_prevista(),
                pedidoDeCompra.getData_pedido()
        );

    }


}
