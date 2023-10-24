package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    private Integer qtde;

    public Produto(ProdutoDTO dto) {
        this.sku = dto.sku();
        this.nome_produto = dto.nome_produto();
        this.qtde = dto.qtde();
    }

    public Produto() {}
}
