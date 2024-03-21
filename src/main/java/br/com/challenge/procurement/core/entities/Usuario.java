package br.com.challenge.procurement.core.entities;

import br.com.challenge.procurement.core.entities.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity(name="usuario")
@Table(name="usuario_procurement")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator = "usuario_procurement_seq")
    Long id;

    String nome;
    @Enumerated(EnumType.STRING)
    Permissao permissao;
    String senha;
    String email;

    public Usuario() {}

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.permissao = dto.permissao();
        this.senha = dto.senha();
        this.email = dto.email();
    }
}
