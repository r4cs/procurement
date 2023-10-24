package br.com.challenge.procurement.model;
import br.com.challenge.procurement.model.DTO.SolicitacaoDeCompraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity(name="solicitacao_compra")
@Table(name = "solicitacao_compra_procurement")
public class SolicitacaoDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "solicitacao_procurement_seq")
    Long id;

    // fk -> onetomany ??
    private String sku;

    private int qtde;
    private BigDecimal valor_unitario;

    @ManyToOne
    @JoinColumn(name = "solicitante")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "aprovador")
    private Usuario aprovador;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String motivo_recusado;
    private LocalDateTime data_solicitacao;

    public SolicitacaoDeCompra(){};

    public SolicitacaoDeCompra(SolicitacaoDeCompraDTO dto){
        this.sku = dto.sku();
        this.qtde = dto.qtde();
        this.valor_unitario = dto.valor_unitario();
        this.solicitante = dto.solicitante();
        this.aprovador = dto.aprovador();
        this.status = dto.status();
        this.motivo_recusado = dto.motivo_recusado();
        this.data_solicitacao = dto.data_solicitacao();
    }
}
