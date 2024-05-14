
package br.com.challenge.procurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
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
                                .requestMatchers(
                                        "/logout",
                                        "/api/**",
                                        "/swagger-ui/**",
                                        "/static/js/**",
                                        "/content").authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .permitAll()
                                .defaultSuccessUrl("/content")
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
                "/v3/api-docs/**", "api/swagger.json/**", "/swagger-resources/**" , "/static/css/**", "https://lookerstudio.google.com/reporting/**"
        );
    }
}
