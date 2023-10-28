package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Produto;
import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.entities.Status;
import br.com.challenge.procurement.core.entities.Usuario;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        Produto sku,
        Integer quantidade,
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
