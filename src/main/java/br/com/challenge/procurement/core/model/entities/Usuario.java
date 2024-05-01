package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Entity(name="usuario")
@Table(name = "usuario_procurement")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "usuario_procurement_seq")
    private Long id;
    private String nome;
    private String senha;
    @Column(unique = true)
    private String email;

    public Usuario() {}

    public Usuario(UsuarioDTO dto) {
        this.nome = dto.nome();
        this.senha = dto.senha();
        this.email = dto.email();
    }
}


