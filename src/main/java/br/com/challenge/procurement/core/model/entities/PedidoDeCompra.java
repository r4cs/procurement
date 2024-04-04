package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.entities.DTO.PedidoDeCompraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity(name="pedido_compra")
@Table(name = "pedido_procurement_sprint3")
public class PedidoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "pedido_procurement_seq_sprint3")
    Long id;
    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoDeCompra solicitacao;

    private TipoDePagamento tipoDePagamento;
    private LocalDateTime data_pedido;

    public PedidoDeCompra(PedidoDeCompraDTO dto) {
        this.solicitacao = dto.solicitacao_id();
        this.tipoDePagamento = dto.tipoDePagamento();
        this.data_pedido = LocalDateTime.now();
    }
    public PedidoDeCompra() {}
}
