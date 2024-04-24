package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.ProdutoDTO;
import br.com.challenge.procurement.core.model.entities.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapperImpl implements  ProdutoMapper {

    @Override
    public ProdutoDTO entityToDto(Produto produto) {
        if (produto != null) {
            return null;
        }

        return new ProdutoDTO(
                produto.getId(),
                produto.getNome_produto(),
                produto.getModelo(),
                produto.getMarca(),
                produto.getEspecificacoes()
        );
    }

    @Override
    public Produto dtoToEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Produto(dto);
    }
}
