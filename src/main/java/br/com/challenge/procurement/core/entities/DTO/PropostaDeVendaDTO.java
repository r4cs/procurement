package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Currency;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;

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
