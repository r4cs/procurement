package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.SolicitacaoDeCompra;
import br.com.challenge.procurement.model.Status;
import br.com.challenge.procurement.model.Usuario;
import jakarta.persistence.Embedded;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        String sku,
        int qtde,
        BigDecimal valor_unitario,
        Usuario solicitante,
        Usuario aprovador,
        @Embedded
        Status status,
        String motivo_recusado,
        LocalDateTime data_solicitacao
) {
    public SolicitacaoDeCompraDTO(SolicitacaoDeCompra solicitacaoDeCompra) {
        this(
                solicitacaoDeCompra.getSku(),
                solicitacaoDeCompra.getQtde(),
                solicitacaoDeCompra.getValor_unitario(),
                solicitacaoDeCompra.getSolicitante(),
                solicitacaoDeCompra.getAprovador(),
                solicitacaoDeCompra.getStatus(),
                solicitacaoDeCompra.getMotivo_recusado(),
                solicitacaoDeCompra.getData_solicitacao()
        );
    }

}
