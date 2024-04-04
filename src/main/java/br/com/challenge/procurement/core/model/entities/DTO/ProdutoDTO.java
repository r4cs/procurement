package br.com.challenge.procurement.core.model.entities.DTO;

import br.com.challenge.procurement.core.model.entities.Produto;
import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(
        Long id,
        @NotBlank
        String nome_produto,
        @NotBlank
        String modelo,
        @NotBlank
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
