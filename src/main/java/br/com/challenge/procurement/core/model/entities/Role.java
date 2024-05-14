package br.com.challenge.procurement.core.model.entities;

import br.com.challenge.procurement.core.model.DTO.RoleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name="role_procurement")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String role;

    public Role(RoleDto dto) {
        this.id = dto.id();
        this.role = dto.role();
    }
}
