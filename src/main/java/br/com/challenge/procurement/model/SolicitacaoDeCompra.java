package br.com.challenge.procurement.model;
import br.com.challenge.procurement.model.DTO.SolicitacaoDeCompraDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity(name="solicitacao")
@Table(name = "solicitacao_procurement")
public class SolicitacaoDeCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "solicitacao_procurement_seq")
    Long id;


    @OneToOne()
    @JoinColumn(name = "sku", foreignKey = @ForeignKey(name="sku"))
    private Produto sku;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante_id;

    @ManyToOne
    @JoinColumn(name = "aprovador_id")
    private Usuario aprovador_id;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String motivo_recusado;
    private LocalDateTime data_solicitacao;

    public SolicitacaoDeCompra(){};

    public SolicitacaoDeCompra(SolicitacaoDeCompraDTO dto){
        this.sku = dto.sku();
        this.quantidade = dto.quantidade();
        this.solicitante_id = dto.solicitante_id();
        this.aprovador_id = dto.aprovador_id();
        this.status = dto.status();
        this.motivo_recusado = dto.motivo_recusado();
        this.data_solicitacao = dto.data_solicitacao();
    }
}
