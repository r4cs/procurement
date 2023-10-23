package br.com.challenge.procurement.model;

import br.com.challenge.procurement.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.model.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name="usuario")
@Table(name = "tb_usuario_procurement")
public class Usuario extends BaseEntity {
    private String nome;
    private String senha;
    private String email;

    public Usuario() {}

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.senha = dto.senha();
        this.email = dto.email();
    }
}


