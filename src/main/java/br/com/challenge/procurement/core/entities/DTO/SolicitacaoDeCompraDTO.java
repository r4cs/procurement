package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Produto;
import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.entities.Status;
import br.com.challenge.procurement.core.entities.Usuario;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        Produto produto,
        Integer quantidade,
        Usuario solicitante,
        @Embedded
        Status status,
        LocalDateTime data_solicitacao
) {
    public SolicitacaoDeCompraDTO(SolicitacaoDeCompra solicitacaoDeCompra) {
        this(
                solicitacaoDeCompra.getProduto(),
                solicitacaoDeCompra.getQuantidade(),
                solicitacaoDeCompra.getSolicitante(),
                solicitacaoDeCompra.getStatus(),
                solicitacaoDeCompra.getData_solicitacao()
        );
    }

}
