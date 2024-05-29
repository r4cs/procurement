package br.com.challenge.procurement.core.controllers.authJwtController;

import br.com.challenge.procurement.core.models.authentication.AuthRequest;
import br.com.challenge.procurement.core.models.authentication.AuthResponse;
import br.com.challenge.procurement.core.models.authentication.RegisterRequest;
import br.com.challenge.procurement.core.services.authentication.FornecedorAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@RestController
@Controller
@RequestMapping(value="/auth/fornecedor")
public class AuthJwtFornecedoresController {
    private final FornecedorAuthService fornecedorService;

    @Autowired
    public AuthJwtFornecedoresController(FornecedorAuthService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<AuthResponse> registerSupplyer(
            @RequestBody RegisterRequest request
    ) {
        return fornecedorService.register(request);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return fornecedorService.authenticate(request);
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        fornecedorService.refreshToken(request, response);
    }
}
