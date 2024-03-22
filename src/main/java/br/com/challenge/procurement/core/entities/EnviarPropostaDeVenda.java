package br.com.challenge.procurement.core.entities;

import br.com.challenge.procurement.core.entities.DTO.EnviarPropostaDeVendaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Entity(name="enviar_proposta_de_venda")
@Table(name = "enviar_proposta_venda_procurement")
public class EnviarPropostaDeVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_compra_id")
    private PedidoDeCompra pedidoDeCompra;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private BigDecimal valorProposta;

    public EnviarPropostaDeVenda() {}

    public EnviarPropostaDeVenda(EnviarPropostaDeVendaDTO dto) {
        this.id = dto.id();
        this.pedidoDeCompra = dto.pedidoDeCompraId();
        this.fornecedor = dto.fornecedorId();
        this.valorProposta = dto.valorProposta();
    }
}
