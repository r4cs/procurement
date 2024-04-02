package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.entities.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.entities.Produto;

public class ProdutoMapper {

    public Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome_produto(dto.nome_produto());
        produto.setMarca(dto.marca());
        produto.setModelo(dto.modelo());
        produto.setEspecificacoes(dto.especificacoes());
        return produto;
    }

    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }
}
