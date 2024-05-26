package br.com.challenge.procurement.core.models.DTO;

import br.com.challenge.procurement.core.models.entities.Funcionario;
import br.com.challenge.procurement.core.models.entities.Produto;
import br.com.challenge.procurement.core.models.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.models.entities.Status;
import jakarta.persistence.Embedded;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record SolicitacaoDeCompraDTO(
        Produto produto,
        @NumberFormat
        Integer quantidade,
        Funcionario solicitante,
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
