package br.com.challenge.procurement.core.authController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/")
    String index(Principal principal) {
        System.out.println("Principal em root /: " + principal);
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

    @GetMapping("/login/github")
    public String loginWithGithub(HttpServletRequest request) {
        String clientId = "dea1fe6183f99a004c90";
        String redirectUri = "http://localhost:8080/login/oauth2/code/github"; // Altere conforme necessário
        String scope = "read:user"; // Escopo de acesso, altere conforme necessário
        String state = "Mm0gKbrHrouZ72vbRvpo8zfKokcB5nC9cqMwPhXeUaE="; // Estado, altere conforme necessário

        // Construindo a URL de login do GitHub
        String githubLoginUrl = "https://github.com/login/oauth/authorize" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=code" +
                "&scope=" + scope +
                "&state=" + state;

        // Redirecionar para a URL de login do GitHub
        return "redirect:" + githubLoginUrl;
    }


}
