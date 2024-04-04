package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.model.entities.Status;
import br.com.challenge.procurement.core.model.entities.Usuario;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        @NotBlank
        Produto produto,
        @NotBlank
        Integer quantidade,
        @NotBlank
        Usuario solicitante,
        @Embedded
        Status status,
        @NotBlank
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
