package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Produto;

import java.math.BigDecimal;

public record ProdutoDTO(
        String sku,
        String nome_produto,
        Integer estoque,
        BigDecimal valor_unitario
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getSku(),
                produto.getNome_produto(),
                produto.getEstoque(),
                produto.getValor_unitario()
        );
    }
}
