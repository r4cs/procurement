package br.com.challenge.procurement.core.models.entities;

import br.com.challenge.procurement.core.models.DTO.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name = "produto")
@Table(name = "produto_procurement")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

