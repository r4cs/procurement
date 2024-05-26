package br.com.challenge.procurement.core.models.authentication;

import br.com.challenge.procurement.core.models.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.models.DTO.FuncionarioDTO;
import br.com.challenge.procurement.core.models.DTO.FornecedorDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nome;
    private String sobrenome;
    private String email;
    private String password;
    private RoleEnum role;
    private String razaoSocial; // Adicionado para Fornecedor
    private String cnpj; // Adicionado para Fornecedor
    private String telefone; // Adicionado para Fornecedor
    private EnderecoDTO endereco; // Adicionado para Fornecedor

    public FuncionarioDTO toUsuarioDTO() {
        return new FuncionarioDTO(
                null, // id será gerado automaticamente
                this.nome,
                this.sobrenome,
                this.email,
                this.password,
                this.role
        );
    }

    public FornecedorDTO toFornecedorDTO() {
        // Adapte para incluir outros atributos específicos de Fornecedor
        return new FornecedorDTO(
                null, // id será gerado automaticamente
                "", // Razao_social
                "", // CNPJ
                this.nome,
                this.sobrenome,
                "", // Telefone
                this.email,
                this.password,
                this.role,
                null // EnderecoDTO
        );
    }
}