package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Produto;

public record ProdutoDTO(
        String sku,
        String nome_produto,
        Integer qtde
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getSku(),
                produto.getNome_produto(),
                produto.getQtde()
        );
    }
}
