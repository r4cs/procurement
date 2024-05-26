package br.com.challenge.procurement.core.models.DTO;

import br.com.challenge.procurement.core.models.entities.Fornecedor;
import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(

        Long id,
        @NotBlank
        String razao_social,
        @NotBlank
        String cnpj,
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        RoleEnum role,
        EnderecoDTO endereco
) {
    public FornecedorDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getId(),
                fornecedor.getRazaoSocial(),
                fornecedor.getCnpj(),
                fornecedor.getNome(),
                fornecedor.getSobrenome(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getSenha(),
                fornecedor.getRole(),
                fornecedor.getEndereco()
        );
    }
}
