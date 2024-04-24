package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapperImpl implements FornecedorMapper {

    @Override
    public FornecedorDTO entityToDto(Fornecedor fornecedor) {
        if (fornecedor == null) {
            return null;
        }
        return new FornecedorDTO(
                fornecedor.getRazao_social(),
                fornecedor.getCnpj(),
                fornecedor.getNome_contato(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getEndereco()
        );
    }

    @Override
    public Fornecedor dtoToEntity(FornecedorDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Fornecedor(dto);
    }
}
