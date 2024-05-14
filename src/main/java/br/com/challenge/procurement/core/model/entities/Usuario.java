package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity(name="usuario")
@Table(name = "usuario_procurement")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name="id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> role = new ArrayList<>();

    public Usuario() {}

    public Usuario(UsuarioDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.email = dto.email();
    }

}
