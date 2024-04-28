package br.com.challenge.procurement.core.model.entities;
import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @OneToOne()
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name="produto_id"))
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime data_solicitacao;

    public SolicitacaoDeCompra(){};

    public SolicitacaoDeCompra(SolicitacaoDeCompraDTO dto){
        this.produto = dto.produto();
        this.quantidade = dto.quantidade();
        this.solicitante = dto.solicitante();
        this.status = dto.status();
        this.data_solicitacao = LocalDateTime.now();
    }
}
