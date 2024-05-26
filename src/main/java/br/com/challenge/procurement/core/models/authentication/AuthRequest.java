package br.com.challenge.procurement.core.models.authentication;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}