package br.com.challenge.procurement.core.authController;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.entities.RoleEnum;
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

    public UsuarioDTO toUsuarioDTO() {
        return new UsuarioDTO(
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



//package br.com.challenge.procurement.core.authController;
//
//import br.com.challenge.procurement.core.model.entities.RoleEnum;
//import lombok.*;
//
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class RegisterRequest {
//
//    private String nome;
//    private String sobrenome;
//    private String email;
//    private String password;
//    private RoleEnum role;
//}