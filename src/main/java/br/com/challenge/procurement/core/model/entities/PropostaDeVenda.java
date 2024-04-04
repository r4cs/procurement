package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Entity(name="proposta_venda")
@Table(name = "proposta_venda_sprint3")
public class PropostaDeVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "proposta_venda_procurement_seq_sprint3")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pedido_compra_id")
    private SolicitacaoDeCompra pedido_compra;
    private BigDecimal valor_unitario;
    private BigDecimal valor_total;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public PropostaDeVenda() {}

    public PropostaDeVenda(PropostaDeVendaDTO dto) {
        this.id = dto.id();
        this.pedido_compra = dto.pedido_compra();
        this.valor_unitario = dto.valor_unitario();
        this.valor_total = dto.valor_total();
        this.fornecedor = dto.fornecedor();
    }
}
