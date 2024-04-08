package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public record PropostaDeVendaDTO(
        Long id,
        SolicitacaoDeCompra pedido_compra,
        @NumberFormat
        BigDecimal valor_unitario,
        @NumberFormat
        BigDecimal valor_total,
        Fornecedor fornecedor) {

    public PropostaDeVendaDTO(PropostaDeVenda propostaDeVenda) {
        this(
                propostaDeVenda.getId(),
                propostaDeVenda.getPedido_compra(),
                propostaDeVenda.getValor_unitario(),
                propostaDeVenda.getValor_total(),
                propostaDeVenda.getFornecedor()
        );
    }
}
