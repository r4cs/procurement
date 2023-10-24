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
    @JoinColumn(name = "id_solicitacao_compra")
    private SolicitacaoDeCompra solicitacaoCompra;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    private LocalDateTime data_entrega_prevista;
    private LocalDateTime data_pedido;

    public PedidoDeCompra(PedidoDeCompraDTO dto) {
        this.solicitacaoCompra = dto.solicitacaoDeCompra();
        this.fornecedor = dto.fornecedor();
        this.data_entrega_prevista = dto.data_entrega_prevista();
        this.data_pedido = dto.data_pedido();
    }

    public PedidoDeCompra() {

    }
}
