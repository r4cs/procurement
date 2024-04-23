package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        String cep
) {

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}
