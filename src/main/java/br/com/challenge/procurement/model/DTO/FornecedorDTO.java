package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Fornecedor;

public record FornecedorDTO(
        String razao_social,
        String cnpj,
        String nome_contato,
        String telefone,
        String email
) {
    public FornecedorDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getRazao_social(),
                fornecedor.getCnpj(),
                fornecedor.getNome_contato(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
        );
    }
}
