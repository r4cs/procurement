package br.com.challenge.procurement.core.model.DTO;

import br.com.challenge.procurement.core.model.entities.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record RoleDto(
        Long id,
        @NotBlank
        String role
) {
    public RoleDto(Role role) {
        this(
                role.getId(),
                role.getRole()
        );
    }
}
