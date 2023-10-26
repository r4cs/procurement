package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.PedidoDeCompraDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "pedido_procurement_seq")
    Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoDeCompra solicitacao;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private LocalDateTime data_entrega_prevista;
    private LocalDateTime data_pedido;

    public PedidoDeCompra(PedidoDeCompraDTO dto) {
        this.solicitacao = dto.solicitacaoDeCompra();
        this.fornecedor = dto.fornecedor();
        this.data_entrega_prevista = dto.data_entrega_prevista();
        this.data_pedido = LocalDateTime.now();
    }

    public PedidoDeCompra() {

    }
}
