package br.com.challenge.procurement.core.models.DTO;

import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import br.com.challenge.procurement.core.models.entities.Funcionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        Long id,
        @NotBlank
        @Size(max=255)
        String nome,
        @NotBlank
        @Size(max=255)
        String sobrenome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        RoleEnum role
) {
    public FuncionarioDTO(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getEmail(),
                funcionario.getSenha(),
                funcionario.getRole()
        );
    }
}
