package br.com.challenge.procurement.core.entities.DTO;

import br.com.challenge.procurement.core.entities.Fornecedor;

public record FornecedorDTO(
        String razao_social,
        String cnpj,
        String nome_contato,
        String telefone,
        String email,
        EnderecoDTO endereco
) {
    public FornecedorDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getRazao_social(),
                fornecedor.getCnpj(),
                fornecedor.getNome_contato(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getEndereco()
        );
    }
}
