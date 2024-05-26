package br.com.challenge.procurement.core.models.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.challenge.procurement.core.models.authentication.Permission.*;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

    ADMIN(
            Set.of(

                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,

                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    USER_CREATE,

                    SUPPLYER_READ,
                    SUPPLYER_UPDATE,
                    SUPPLYER_DELETE,
                    SUPPLYER_CREATE

            )
    ),
    USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    USER_CREATE
            )
    ),

    SUPPLYER(
            Set.of(
                    SUPPLYER_READ,
                    SUPPLYER_UPDATE,
                    SUPPLYER_DELETE,
                    SUPPLYER_CREATE
            )
    )

    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        System.out.println("\n\n*** Iniciando DEBUG em RoleEnum - getAuthorities(): ");
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        System.out.println("* var authorities: " + authorities);
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        System.out.println("* var authorities add SimpleGrantedAuth: " + authorities);
        return authorities;
    }
}
