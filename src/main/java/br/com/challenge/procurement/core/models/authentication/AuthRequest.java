package br.com.challenge.procurement.core.models.authentication;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public record AuthRequest (
        String email,
        String password
) { }