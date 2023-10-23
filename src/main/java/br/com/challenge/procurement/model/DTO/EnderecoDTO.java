package br.com.challenge.procurement.model.DTO;

import br.com.challenge.procurement.model.Endereco;

public record EnderecoDTO(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
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
