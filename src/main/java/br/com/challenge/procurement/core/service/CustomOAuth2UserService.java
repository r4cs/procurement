package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.entities.Role;
import br.com.challenge.procurement.core.model.entities.RoleName;
import br.com.challenge.procurement.core.model.entities.Usuario;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.repositories.UsuarioRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepo usuarioRepo;
    private final FornecedorRepo fornecedorRepo;

    public CustomOAuth2UserService(UsuarioRepo usuarioRepo, FornecedorRepo fornecedorRepo) {
        this.usuarioRepo = usuarioRepo;
        this.fornecedorRepo = fornecedorRepo;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");

        if (email != null) {
            Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(email);
            Optional<Fornecedor> fornecedorOptional = fornecedorRepo.findByEmail(email);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Set<GrantedAuthority> authorities = new HashSet<>();
                for (Role role : usuario.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role.getName().name()));
                }

                // Additional logic to assign roles based on email
                if ("r.guzansky@hotmail.com".equals(email)) {
                    authorities.add(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.name()));
                } else {
                    authorities.add(new SimpleGrantedAuthority(RoleName.ROLE_USER.name()));
                }

                return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
            }

            if (fornecedorOptional.isPresent()) {
                Fornecedor fornecedor = fornecedorOptional.get();
                Set<GrantedAuthority> authorities = new HashSet<>();
                for (Role role : fornecedor.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role.getName().name()));
                }
                authorities.add(new SimpleGrantedAuthority(RoleName.ROLE_SUPPLYER.name()));

                return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
            }


        }

        return oauth2User;
    }
}
