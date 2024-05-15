package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FornecedorDTO(
        @NotBlank
        String razao_social,
        @NotBlank
        String cnpj,
        @NotBlank
        String nome_contato,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,

        @NotBlank
        List<Role> roles,
        EnderecoDTO endereco
) {
    public FornecedorDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getRazao_social(),
                fornecedor.getCnpj(),
                fornecedor.getNome_contato(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getRoles(),
                fornecedor.getEndereco()
        );
    }
}
