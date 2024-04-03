package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PropostaDeVendaDTO(
        Long id,
        @NotBlank
//        @NumberFormat
        SolicitacaoDeCompra solicitacao_de_compra,
        @NotBlank
        BigDecimal valor_unitario,
        @NotBlank
        BigDecimal valor_total,

        @NotBlank
        Fornecedor fornecedor) {

    public PropostaDeVendaDTO(PropostaDeVenda propostaDeVenda) {
        this(
                propostaDeVenda.getId(),
                propostaDeVenda.getSolicitacao_compra(),
                propostaDeVenda.getValor_unitario(),
                propostaDeVenda.getValor_total(),
                propostaDeVenda.getFornecedor()
        );
    }
}
