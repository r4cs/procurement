package br.com.challenge.procurement.core.models.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),

    SUPPLYER_READ("user:read"),
    SUPPLYER_UPDATE("user:update"),
    SUPPLYER_CREATE("user:create"),
    SUPPLYER_DELETE("user:delete");

    private final String permission;
}
