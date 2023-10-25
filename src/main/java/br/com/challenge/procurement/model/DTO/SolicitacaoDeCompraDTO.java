package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Produto;
import br.com.challenge.procurement.model.SolicitacaoDeCompra;
import br.com.challenge.procurement.model.Status;
import br.com.challenge.procurement.model.Usuario;
import jakarta.persistence.Embedded;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        Produto sku,
        int quantidade,
        Usuario solicitante_id,
        Usuario aprovador_id,
        @Embedded
        Status status,
        String motivo_recusado,
        LocalDateTime data_solicitacao
) {
    public SolicitacaoDeCompraDTO(SolicitacaoDeCompra solicitacaoDeCompra) {
        this(
                solicitacaoDeCompra.getSku(),
                solicitacaoDeCompra.getQuantidade(),
                solicitacaoDeCompra.getSolicitante_id(),
                solicitacaoDeCompra.getAprovador_id(),
                solicitacaoDeCompra.getStatus(),
                solicitacaoDeCompra.getMotivo_recusado(),
                solicitacaoDeCompra.getData_solicitacao()
        );
    }

}
