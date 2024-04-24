package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.model.entities.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public EnderecoDTO entityToDto(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDTO(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    @Override
    public Endereco dtoToEntity(EnderecoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Endereco(dto);
    }
}
