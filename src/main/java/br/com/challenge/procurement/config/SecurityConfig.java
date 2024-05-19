package br.com.challenge.procurement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.HashSet;
import java.util.Set;

import static br.com.challenge.procurement.core.model.entities.Permission.*;
import static br.com.challenge.procurement.core.model.entities.RoleEnum.ADMIN;
import static br.com.challenge.procurement.core.model.entities.RoleEnum.USER;
import static br.com.challenge.procurement.core.model.entities.RoleEnum.SUPPLYER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(_cors ->
                        _cors.configurationSource(request -> {
                                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                                    corsConfiguration.addAllowedOrigin("*");
                                    corsConfiguration.addAllowedHeader("*");
                                    corsConfiguration.addAllowedMethod("*");
                                    return corsConfiguration;
                        })
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/login").permitAll()

                                // SO ADMINS
                                .requestMatchers(
                                        "/api/usuario/**",
                                        "/api/fornecedor/**").hasRole(ADMIN.name())
                                .requestMatchers(
                                        GET, "/api/usuario/**",
                                        "/api/fornecedor/**").hasAnyAuthority(ADMIN_READ.name())
                                .requestMatchers(
                                        POST, "/api/usuario/**",
                                        "/api/fornecedor/**").hasAnyAuthority(ADMIN_CREATE.name())
                                .requestMatchers(
                                        PATCH, "/api/usuario/**",
                                        "/api/fornecedor/**").hasAnyAuthority(ADMIN_UPDATE.name())
                                .requestMatchers(
                                        DELETE, "/api/usuario/**",
                                        "/api/fornecedor/**").hasAnyAuthority(ADMIN_DELETE.name())


                                // USERS e ADMINS
                                .requestMatchers(
                                        "/api/produto/**",
                                        "/api/solicitacao/**",
                                        "/api/pedido/**")
                                            .hasAnyRole( USER.name(), ADMIN.name())
                                .requestMatchers(
                                        GET, "/api/produto/**",
                                        "/api/solicitacao/**",
                                        "/api/pedido/**")
                                            .hasAnyAuthority( USER_READ.name(), ADMIN_READ.name())
                                .requestMatchers(
                                        POST, "/api/produto/**",
                                        "/api/solicitacao/**",
                                        "/api/pedido/**")
                                            .hasAnyAuthority(ADMIN_CREATE.name(), ADMIN_CREATE.name())
                                .requestMatchers(
                                        PATCH, "/api/produto/**",
                                        "/api/solicitacao/**",
                                        "/api/pedido/**")
                                            .hasAnyAuthority(ADMIN_UPDATE.name(), ADMIN_UPDATE.name())
                                .requestMatchers(
                                        DELETE, "/api/produto/**",
                                        "/api/solicitacao/**",
                                        "/api/pedido/**")
                                            .hasAnyAuthority( USER_DELETE.name(), ADMIN_DELETE.name())


                                //  SUPPLYERs e ADMINs
                                .requestMatchers(
                                        "/api/proposta/**")
                                            .hasAnyRole(SUPPLYER.name(), ADMIN.name())
                                .requestMatchers(
                                        GET, "/api/proposta/**")
                                            .hasAnyAuthority(SUPPLYER_READ.name(), ADMIN_READ.name())
                                .requestMatchers(
                                        POST, "/api/proposta/**")
                                            .hasAnyAuthority(SUPPLYER_READ.name(), ADMIN_CREATE.name())
                                .requestMatchers(
                                        PATCH, "/api/proposta/**")
                                            .hasAnyAuthority(SUPPLYER_READ.name(), ADMIN_UPDATE.name())
                                .requestMatchers(
                                        DELETE, "/api/proposta/**")
                                            .hasAnyAuthority(SUPPLYER_READ.name(), ADMIN_DELETE.name())



                                // autenticados
                                .requestMatchers(
                                        "/logout",
                                        "/api/**",
                                        "/swagger-ui/**",
                                        "/js/**",
                                        "/content").authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .permitAll()
                                .defaultSuccessUrl("/")
                ).logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/v3/api-docs/**", "api/swagger.json/**", "/swagger-resources/**" ,
                "/css/**", "https://lookerstudio.google.com/reporting/**"
        );
    }

    @Bean
    public OidcUserService oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return new OidcUserService() {
            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) {
                OidcUser oidcUser = delegate.loadUser(userRequest);
                Set<SimpleGrantedAuthority> mappedAuthorities = new HashSet<>();

                // Here you can load the roles from database or any other source
                // and map them to the authenticated user
                String email = oidcUser.getEmail();

                if (email != null) {
                    // Add your role mapping logic here
                    if ("r.guzansky@hotmail.com".equals(email)) {
                        mappedAuthorities.add(new SimpleGrantedAuthority(ADMIN.name()));
                    }
                    else if (email.contains("@gmail.com")) {
                        mappedAuthorities.add(new SimpleGrantedAuthority(USER.name()));
                    } else {
//                    } else if (email.contains("@outlook.com")){
                        // Example: Assuming all other users are supplyers for simplicity
                        mappedAuthorities.add(new SimpleGrantedAuthority(SUPPLYER.name()));
                    }
                }

                oidcUser.getAuthorities().forEach(authority -> {
                    if (authority instanceof OidcUserAuthority) {
                        mappedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                    } else {
                        mappedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                    }
                });

                return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
            }
        };
    }

}