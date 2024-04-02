package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Produto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome_produto,
        String modelo,
        String marca,
        String especificacoes) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome_produto(),
                produto.getModelo(),
                produto.getMarca(),
                produto.getEspecificacoes()
        );
    }
}
