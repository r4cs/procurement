package br.com.challenge.procurement.core.authController;

import br.com.challenge.procurement.core.model.entities.*;
import br.com.challenge.procurement.core.service.authentication.UsuarioAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    private final UsuarioAuthService service;

    @Autowired
    public AuthController(UsuarioAuthService service) {
        this.service = service;
    }

    @GetMapping("/")
    String index(Principal principal, Model model) {
        System.out.println("Principal em root /: " + principal);
        List<Class<?>> entidades = Arrays.asList(
                Fornecedor.class,
                PedidoDeCompra.class,
                Produto.class,
                PropostaDeVenda.class,
                SolicitacaoDeCompra.class,
                Usuario.class);
        model.addAttribute("entidades", entidades);
        List<String> endpoints = entidades.stream()
                .map(e -> e.getSimpleName().split("(?=[A-Z])")[0])
                .collect(Collectors.toList());
        model.addAttribute("endpoints", endpoints);

        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @GetMapping("/login")
    String login() {
        return "/home/homeNotSignedIn";
    }

    @GetMapping("/logout")
    String logout() {
        return "/home/homeNotSignedIn";
    }

    @PostMapping("/register-supplyer")
    public ResponseEntity<AuthResponse> register_Supplyer(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerSupplier(request));
    }

    @PostMapping("/register-user")
    public ResponseEntity<AuthResponse> register_User(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerSupplier(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}