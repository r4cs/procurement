package br.com.challenge.procurement.core.service.authentication;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.authentication.Permission;
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
        Set<GrantedAuthority> authorities = new HashSet<>();
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(email);
        Optional<Fornecedor> fornecedorOptional = fornecedorRepo.findByEmail(email);

        if (email != null) {

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                authorities.add(new SimpleGrantedAuthority(usuario.getRole().name()));
                }

                // Additional logic to assign roles based on email
                if ("r.guzansky@hotmail.com".equals(email)) {
                    authorities.add(new SimpleGrantedAuthority(Permission.ADMIN_CREATE.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.ADMIN_READ.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.ADMIN_UPDATE.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.ADMIN_DELETE.name()));
//                    authorities.add(new SimpleGrantedAuthority(Permission.ROLE_ADMIN.name()));
                } else {
                    authorities.add(new SimpleGrantedAuthority(Permission.USER_CREATE.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.USER_READ.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.USER_UPDATE.name()));
                    authorities.add(new SimpleGrantedAuthority(Permission.USER_DELETE.name()));
//                    authorities.add(new SimpleGrantedAuthority(Permission.ROLE_USER.name()));
                }

                return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
            }

            if (fornecedorOptional.isPresent()) {
                Fornecedor fornecedor = fornecedorOptional.get();
                authorities.add(new SimpleGrantedAuthority(Permission.SUPPLYER_CREATE.name()));
                authorities.add(new SimpleGrantedAuthority(Permission.SUPPLYER_READ.name()));
                authorities.add(new SimpleGrantedAuthority(Permission.SUPPLYER_UPDATE.name()));
                authorities.add(new SimpleGrantedAuthority(Permission.SUPPLYER_DELETE.name()));
//                authorities.add(new SimpleGrantedAuthority(Permission.ROLE_SUPPLYER.name()));

                return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
            }

        return oauth2User;
    }
}
