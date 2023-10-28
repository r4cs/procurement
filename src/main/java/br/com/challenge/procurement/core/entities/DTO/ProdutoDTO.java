package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Produto;

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
