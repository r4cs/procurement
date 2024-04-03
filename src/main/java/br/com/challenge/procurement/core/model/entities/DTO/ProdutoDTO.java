package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.Produto;

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
