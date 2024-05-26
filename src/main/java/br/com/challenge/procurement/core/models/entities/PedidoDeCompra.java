package br.com.challenge.procurement.core.models.entities;

import br.com.challenge.procurement.core.models.DTO.PedidoDeCompraDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity(name="pedido_compra")
@Table(name = "pedido_procurement")
public class PedidoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
