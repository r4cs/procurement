package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.EnviarPropostaDeVenda;
import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.entities.PedidoDeCompra;

import java.math.BigDecimal;

public record EnviarPropostaDeVendaDTO(
        Long id,
        PedidoDeCompra pedidoDeCompraId,
        Fornecedor fornecedorId,
        BigDecimal valorProposta
) {

    public EnviarPropostaDeVendaDTO(EnviarPropostaDeVenda propostaDeVenda) {
        this(
                propostaDeVenda.getId(),
                propostaDeVenda.getPedidoDeCompra(),
                propostaDeVenda.getFornecedor(),
                propostaDeVenda.getValorProposta()
        );
    }
}
