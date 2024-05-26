package br.com.challenge.procurement.core.controllers.authJwtController;

import br.com.challenge.procurement.core.models.authentication.AuthRequest;
import br.com.challenge.procurement.core.models.authentication.AuthResponse;
import br.com.challenge.procurement.core.models.authentication.RegisterRequest;
import br.com.challenge.procurement.core.services.authentication.FuncionarioAuthService;
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

@RestController
@Controller
@RequestMapping(value="/auth/funcionario")
public class AuthJwtFuncionariosController {
    private final FuncionarioAuthService funcionarioService;

    @Autowired
    public AuthJwtFuncionariosController(FuncionarioAuthService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(funcionarioService.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(funcionarioService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        funcionarioService.refreshToken(request, response);
    }

}
