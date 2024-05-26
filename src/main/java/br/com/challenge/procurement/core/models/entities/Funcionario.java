package br.com.challenge.procurement.core.models.entities;

import br.com.challenge.procurement.core.models.DTO.FuncionarioDTO;
import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name="funcionario_procurement")
public class Funcionario extends BaseUser {


    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String sobrenome, String email, String senha, RoleEnum role) {
        super();
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setRole(role);
    }

    public Funcionario(FuncionarioDTO dto) {
        this(dto.nome(), dto.sobrenome(), dto.email(), dto.senha(), dto.role());
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + getNome() +'\'' +
                ", sobrenome='" + getSobrenome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
