package br.com.challenge.procurement.core.model.entities;

import lombok.Getter;

@Getter
public enum RoleName {
    ROLE_SUPPLYER("SUPPLYER"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String name;

    RoleName(String name) {
        this.name=name;
    }
}
