package br.com.challenge.procurement.core.services.authentication;

import br.com.challenge.procurement.core.models.authentication.AuthRequest;
import br.com.challenge.procurement.core.models.authentication.AuthResponse;
import br.com.challenge.procurement.core.models.authentication.RegisterRequest;
import br.com.challenge.procurement.core.models.authentication.UserToken;
import br.com.challenge.procurement.core.models.entities.BaseUser;
import br.com.challenge.procurement.core.models.entities.Fornecedor;
import br.com.challenge.procurement.core.models.authentication.RoleEnum;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.repositories.authentication.UserTokenRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FornecedorAuthService {
    private final FornecedorRepo fornecedorRepo;
    private final UserTokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(FornecedorAuthService.class);


    @Autowired
    public FornecedorAuthService(FornecedorRepo fornecedorRepo, UserTokenRepo tokenRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.fornecedorRepo = fornecedorRepo;
        this.tokenRepo = tokenRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {

        Fornecedor user = new Fornecedor(
                request.getNome(),
                request.getSobrenome(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                RoleEnum.SUPPLYER
        );
        user.setRazaoSocial(request.getRazaoSocial());
        user.setCnpj(request.getCnpj());
        user.setTelefone(request.getTelefone());
        user.setEndereco(request.getEndereco());

//        System.out.println("* Fornecedor POST request em /register: " + user);

        Fornecedor savedUser = fornecedorRepo.save(user);
        String jwtToken = null;
        String refreshToken = null;
        try {
            jwtToken = jwtService.generateToken(savedUser);
            refreshToken = jwtService.generateRefreshToken(savedUser);
        } catch (Exception e) {
            logger.error("Erro ao gerar o token para o usuário: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao gerar o token para o usuário", e);
        }

        saveUserToken(savedUser, jwtToken);
        return new AuthResponse(jwtToken, refreshToken);
    }

    public AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
        try {
            System.out.println("\n\n*** Iniciando DEBUG em FornecedorAuthService - authenticate(): ");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                                                                                request.getEmail(),
                                                                                                request.getPassword()
            );
            System.out.println("* usernamePasswordAuthenticationToken: " + usernamePasswordAuthenticationToken);
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException exception) {
            logger.error("** !! Exception !! ** --->" + exception.getMessage());
            logger.error("** !! AT !! ** --->" + exception.getCause());
            throw exception;
        }
            Optional<BaseUser> userOpt = fornecedorRepo.findByEmail(request.getEmail())
                    .map((Function<? super Fornecedor, ? extends Optional<BaseUser>>) Optional::of)
                    .orElseThrow();
            BaseUser user = userOpt.orElseThrow();
            System.out.println("* BaseUser user = userOpt.orElseThrow(): " + user);
            String jwtToken = jwtService.generateToken(user);
            System.out.println("* jwtToken = jwtService.generateToken(user): " + jwtToken);
            String refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return new AuthResponse(jwtToken, refreshToken);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("\n\n*** Iniciando DEBUG em FornecedorAuthService - refreshToken(...): ");
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("authHeader: " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        final String refreshToken = authHeader.substring(7);
        System.out.println("refreshToken: " + refreshToken);
        final String userEmail = jwtService.extractUsername(refreshToken);
        System.out.println("userEmail: " + userEmail);
        if (userEmail != null) {
            Optional<BaseUser> userOpt = fornecedorRepo.findByEmail(userEmail)
                    .map((Function<? super Fornecedor, ? extends Optional<BaseUser>>) Optional::of)
                    .orElseThrow();
            BaseUser user = userOpt.orElseThrow();
            System.out.println("* BaseUser user = userOpt.orElseThrow(): " + user);
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(BaseUser user, String jwtToken) {{
        System.out.println("\n\n*** Iniciando DEBUG em FornecedorAuthService - saveUserToken(...): ");
        UserToken token = new UserToken();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        System.out.println("* Token fornecedor: " + token.getToken());
        tokenRepo.save(token);
        System.out.println("* Token salve com sucesso, token: " + token.getToken());
    }}

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


/*
//package br.com.challenge.procurement.core.service.authentication;
//
//import br.com.challenge.procurement.core.model.authentication.AuthRequest;
//import br.com.challenge.procurement.core.model.authentication.AuthResponse;
//import br.com.challenge.procurement.core.model.authentication.RegisterRequest;
//import br.com.challenge.procurement.core.model.authentication.UserToken;
//import br.com.challenge.procurement.core.service.authentication.AuthDetails;
//import br.com.challenge.procurement.core.model.entities.BaseUser;
//import br.com.challenge.procurement.core.model.entities.Fornecedor;
//import br.com.challenge.procurement.core.model.authentication.RoleEnum;
//import br.com.challenge.procurement.core.repositories.FornecedorRepo;
//import br.com.challenge.procurement.core.service.authentication.TokenType;
//import br.com.challenge.procurement.core.repositories.authentication.UserTokenRepo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Service
//@RequiredArgsConstructor
//public class FornecedorAuthService {
//    private final FornecedorRepo fornecedorRepo;
//    private final UserTokenRepo tokenRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthResponse register(RegisterRequest request) {
//        Fornecedor user = new Fornecedor(request.getNome(), request.getSobrenome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), RoleEnum.USER);
//        Fornecedor savedUser = fornecedorRepo.save(user);
//        String jwtToken = jwtService.generateToken((AuthDetails) savedUser);
//        String refreshToken = jwtService.generateRefreshToken((AuthDetails) savedUser);
//        saveUserToken(savedUser, jwtToken);
//        return new AuthResponse(jwtToken, refreshToken);
//    }
//
//
//    public AuthResponse authenticate(AuthRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        Optional<BaseUser> userOpt = fornecedorRepo.findByEmail(request.getEmail())
//                .map((Function<? super Fornecedor, ? extends Optional<BaseUser>>) Optional::of)
//                .orElseThrow();
//        BaseUser user = userOpt.orElseThrow();
//        String jwtToken = jwtService.generateToken((AuthDetails) user);
//        String refreshToken = jwtService.generateRefreshToken((AuthDetails) user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return new AuthResponse(jwtToken, refreshToken);
//    }
//
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        final String refreshToken = authHeader.substring(7);
//        final String userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            Optional<BaseUser> userOpt = fornecedorRepo.findByEmail(userEmail)
//                    .map((Function<? super Fornecedor, ? extends Optional<BaseUser>>) Optional::of)
//                    .orElseThrow();
//            BaseUser user = userOpt.orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, (AuthDetails) user)) {
//                String accessToken = jwtService.generateToken((AuthDetails) user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                AuthResponse authResponse = new AuthResponse(accessToken, refreshToken);
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
//
//    private void saveUserToken(BaseUser user, String jwtToken) {
//        UserToken token = new UserToken();
//        token.setUser(user);
////        token.setFornecedor(user);
//        token.setToken(jwtToken);
//        token.setTokenType(TokenType.BEARER);
//        token.setExpired(false);
//        token.setRevoked(false);
//        tokenRepo.save(token);
//    }
//
//    private void revokeAllUserTokens(BaseUser user) {
//        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty()) return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepo.saveAll(validUserTokens);
//    }
//}

 */
