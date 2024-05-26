package br.com.challenge.procurement.core.models.entities;

import br.com.challenge.procurement.core.models.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.models.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name="fornecedor_procurement")
public class Fornecedor extends BaseUser {

    private String razaoSocial;
    private String cnpj;
    private String telefone;
    @Embedded
    private EnderecoDTO endereco;

    public Fornecedor() {}

    public Fornecedor(FornecedorDTO dto) {
        super();
        this.razaoSocial = dto.razao_social();
        this.cnpj = dto.cnpj();
        this.telefone = dto.telefone();
        this.setNome(dto.nome());
        this.setSobrenome(dto.sobrenome());
        this.setEmail(dto.email());
        this.setSenha(dto.senha());
        this.setRole(dto.role());
        this.endereco = getEndereco();
    }

    public Fornecedor(String razao_social, String cnpj, String telefone, String nome, String sobrenome, String email, String senha, RoleEnum role, Endereco endereco) {
        super();
        this.razaoSocial = razao_social;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setRole(role);
        this.endereco = getEndereco();
    }

    public Fornecedor(String nome, String sobrenome, String email, String senha, RoleEnum role) {
        super();
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setRole(role);
    }





    @Override
    public String toString() {
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", contato='" + getNome() + ' '+ getSobrenome() +'\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
