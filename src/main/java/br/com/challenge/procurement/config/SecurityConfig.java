package br.com.challenge.procurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
/*  //              .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

 */
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
                                .requestMatchers("/", "/login", "/login/**").permitAll()
                                .requestMatchers(
                                        "/logout",
                                        "/swagger-ui/**",
                                        "/api/**",
                                        "/home/homeSignedIn").authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .permitAll()
                                .defaultSuccessUrl("/swagger-ui/index.html")
                                .failureUrl("/")

                ).logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                );
        System.out.println("*** http security: " + http);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/v3/api-docs/**", "/api/swagger.json/**", "/swagger-resources/**"
        );
    }
}