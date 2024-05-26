package br.com.challenge.procurement.core.services.authentication;

import br.com.challenge.procurement.core.models.authentication.AuthRequest;
import br.com.challenge.procurement.core.models.authentication.AuthResponse;
import br.com.challenge.procurement.core.models.authentication.RegisterRequest;
import br.com.challenge.procurement.core.models.authentication.UserToken;
import br.com.challenge.procurement.core.models.entities.BaseUser;
import br.com.challenge.procurement.core.models.entities.Funcionario;
import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import br.com.challenge.procurement.core.repositories.FuncionarioRepo;
import br.com.challenge.procurement.core.repositories.authentication.UserTokenRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

@Service
public class FuncionarioAuthService {
    private final FuncionarioRepo funcionarioRepo;
    private final UserTokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public FuncionarioAuthService(FuncionarioRepo funcionarioRepo, UserTokenRepo tokenRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.funcionarioRepo = funcionarioRepo;
        this.tokenRepo = tokenRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {
        Funcionario user = new Funcionario(request.getNome(), request.getSobrenome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), RoleEnum.USER);
        Funcionario savedUser = funcionarioRepo.save(user);
        String jwtToken = jwtService.generateToken((AuthDetails) savedUser);
        String refreshToken = jwtService.generateRefreshToken((AuthDetails) savedUser);
        saveUserToken(savedUser, jwtToken);
        return new AuthResponse(jwtToken, refreshToken);
    }


    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Optional<BaseUser> userOpt = funcionarioRepo.findByEmail(request.getEmail())
                .map((Function<? super Funcionario, ? extends Optional<BaseUser>>) Optional::of)
                .orElseThrow();
        BaseUser user = userOpt.orElseThrow();
        String jwtToken = jwtService.generateToken((AuthDetails) user);
        String refreshToken = jwtService.generateRefreshToken((AuthDetails) user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthResponse(jwtToken, refreshToken);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            Optional<BaseUser> userOpt = funcionarioRepo.findByEmail(userEmail)
                    .map((Function<? super Funcionario, ? extends Optional<BaseUser>>) Optional::of)
                    .orElseThrow();
            BaseUser user = userOpt.orElseThrow();
            if (jwtService.isTokenValid(refreshToken, (AuthDetails) user)) {
                String accessToken = jwtService.generateToken((AuthDetails) user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(BaseUser user, String jwtToken) {
        UserToken token = new UserToken();
        token.setUser(user);
//        token.setFuncionario(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepo.save(token);
    }

    private void revokeAllUserTokens(BaseUser user) {
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }
}



//package br.com.challenge.procurement.core.service.authentication;
//
//import br.com.challenge.procurement.core.model.authentication.AuthRequest;
//import br.com.challenge.procurement.core.model.authentication.AuthResponse;
//import br.com.challenge.procurement.core.model.authentication.RegisterRequest;
//import br.com.challenge.procurement.core.model.entities.BaseUser;
//import br.com.challenge.procurement.core.model.authentication.RoleEnum;
//import br.com.challenge.procurement.core.repositories.FornecedorRepo;
//import br.com.challenge.procurement.core.repositories.FuncionarioRepo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@RequiredArgsConstructor
//public class FuncionarioAuthService {
//    private final FuncionarioRepo funcionarioRepo;
//    private final FornecedorRepo fornecedorRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthResponse registerUser(RegisterRequest request) {
//        var user = BaseUser.builder()
//                .nome(request.getNome())
//                .sobrenome(request.getSobrenome())
//                .email(request.getEmail())
//                .senha(passwordEncoder.encode(request.getPassword()))
//                .role(RoleEnum.USER)
//                .build();
//        var savedUser = funcionarioRepo.save(user);
//        var jwtToken = jwtService.generateToken(savedUser);
//        var refreshToken = jwtService.generateRefreshToken(savedUser);
//        saveUserToken(savedUser, jwtToken);
//        return AuthResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    public AuthResponse registerSupplier(RegisterRequest request) {
//        var supplier = BaseUser.builder()
//                .nome(request.getNome())
//                .sobrenome(request.getSobrenome())
//                .email(request.getEmail())
//                .senha(passwordEncoder.encode(request.getPassword()))
//                .role(RoleEnum.SUPPLIER)
//                .build();
//        var savedSupplier = fornecedorRepo.save(supplier);
//        var jwtToken = jwtService.generateToken(savedSupplier);
//        var refreshToken = jwtService.generateRefreshToken(savedSupplier);
//        saveUserToken(savedSupplier, jwtToken);
//        return AuthResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    public AuthResponse authenticate(AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        BaseUser user = funcionarioRepo.findByEmail(request.getEmail())
//                .orElseGet(() -> fornecedorRepo.findByEmail(request.getEmail())
//                        .orElseThrow());
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            BaseUser user = funcionarioRepo.findByEmail(userEmail)
//                    .orElseGet(() -> fornecedorRepo.findByEmail(userEmail)
//                            .orElseThrow());
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
//
//    private void saveUserToken(BaseUser user, String jwtToken) {
//        // Implemente o salvamento do token do usuário conforme necessário
//    }
//
//    private void revokeAllUserTokens(BaseUser user) {
//        // Implemente a revogação de todos os tokens do usuário conforme necessário
//    }
//}
