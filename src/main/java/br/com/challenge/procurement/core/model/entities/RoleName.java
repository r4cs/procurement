package br.com.challenge.procurement.core.model.entities;

import lombok.Getter;

@Getter
public enum RoleName {
    SUPPLYER("ROLE_SUPPLYER"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    RoleName(String name) {
        this.name=name;
    }
}
