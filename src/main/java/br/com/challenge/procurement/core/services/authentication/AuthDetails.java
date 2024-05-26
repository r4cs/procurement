package br.com.challenge.procurement.core.services.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface AuthDetails extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();

    Long getId();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
