package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.entities.Endereco;
import br.com.challenge.procurement.core.entities.Fornecedor;

public class FornecedorMapper {

    public Fornecedor toEntity(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazao_social(fornecedorDTO.razao_social());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setNome_contato(fornecedorDTO.nome_contato());
        fornecedor.setTelefone(fornecedorDTO.telefone());
        fornecedor.setEmail(fornecedorDTO.email());
        // nao funciona ):
//        fornecedor.setEndereco(new EnderecoMapper().toEntity(fornecedorDTO.endereco()))));
        return fornecedor;
    }

    public FornecedorDTO toDTO(Fornecedor fornecedor) {
        return new FornecedorDTO(fornecedor);
    }
}
