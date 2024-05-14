package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.RoleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="role_procurement")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    public Role() {}

    public Role(RoleDto dto) {
        this.id = dto.id();
        this.role = dto.role();
    }
}
