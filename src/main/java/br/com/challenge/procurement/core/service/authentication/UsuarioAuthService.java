package br.com.challenge.procurement.core.service.authentication;

import br.com.challenge.procurement.core.authController.AuthRequest;
import br.com.challenge.procurement.core.authController.AuthResponse;
import br.com.challenge.procurement.core.authController.RegisterRequest;
import br.com.challenge.procurement.core.model.entities.BaseUser;
import br.com.challenge.procurement.core.model.entities.RoleEnum;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.repositories.UsuarioRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UsuarioAuthService {
    private final UsuarioRepo usuarioRepo;
    private final FornecedorRepo fornecedorRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUser(RegisterRequest request) {
        var user = BaseUser.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .build();
        var savedUser = usuarioRepo.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse registerSupplier(RegisterRequest request) {
        var supplier = BaseUser.builder()
                .nome(request.getNome())
                .sobrenome(request.getSobrenome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.SUPPLIER)
                .build();
        var savedSupplier = fornecedorRepo.save(supplier);
        var jwtToken = jwtService.generateToken(savedSupplier);
        var refreshToken = jwtService.generateRefreshToken(savedSupplier);
        saveUserToken(savedSupplier, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        BaseUser user = usuarioRepo.findByEmail(request.getEmail())
                .orElseGet(() -> fornecedorRepo.findByEmail(request.getEmail())
                        .orElseThrow());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            BaseUser user = usuarioRepo.findByEmail(userEmail)
                    .orElseGet(() -> fornecedorRepo.findByEmail(userEmail)
                            .orElseThrow());
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(BaseUser user, String jwtToken) {
        // Implemente o salvamento do token do usuário conforme necessário
    }

    private void revokeAllUserTokens(BaseUser user) {
        // Implemente a revogação de todos os tokens do usuário conforme necessário
    }
}
