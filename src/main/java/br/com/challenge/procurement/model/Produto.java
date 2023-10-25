package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Entity(name = "produto")
@Table(name = "produto_procurement")
public class Produto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String sku;
    private String nome_produto;
    private Integer estoque;
    private BigDecimal valor_unitario;


    public Produto(ProdutoDTO dto) {
        this.sku = dto.sku();
        this.nome_produto = dto.nome_produto();
        this.estoque = dto.estoque();
        this.valor_unitario = dto.valor_unitario();
    }

    public Produto() {}
}
