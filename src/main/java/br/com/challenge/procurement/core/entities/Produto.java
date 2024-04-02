package br.com.challenge.procurement.core.entities;

import br.com.challenge.procurement.core.entities.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Entity(name = "produto")
@Table(name = "produto_procurement_sprint3")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "produto_procurement_seq_sprint3")
    private Long id;
    private String nome_produto;
    private String modelo;
    private String marca;
    private String especificacoes;


    public Produto(ProdutoDTO dto) {
        this.id = dto.id();
        this.nome_produto = dto.nome_produto();
        this.modelo = dto.modelo();
        this.marca = dto.marca();
        this.especificacoes = dto.especificacoes();
    }

    public Produto() {}

}

