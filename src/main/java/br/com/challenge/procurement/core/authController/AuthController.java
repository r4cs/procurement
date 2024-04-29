package br.com.challenge.procurement.core.authController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/")
    String index(Principal principal) {
        logger.debug("Principal em root /: {}", principal);
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @GetMapping("/login")
    String login() {
        logger.debug("Acessando a página de login");
        return "/home/homeNotSignedIn";
    }

    @GetMapping("/logout")
    String logout() {
        logger.debug("Acessando a página de logout");
        return "/home/homeNotSignedIn";
    }

    @GetMapping("/login/github")
    public String loginWithGithub(HttpServletRequest request) {
        logger.debug("Redirecionando para login do GitHub");
        String redirectUri = "https://app-procurement.azurewebsites.net/.auth/login/github/callback";
        String clientId = "dea1fe6183f99a004c90";
        String githubLoginUrl = "https://github.com/login/oauth2/authorize" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri;
        return "redirect:" + githubLoginUrl;
    }

    @GetMapping("/login/google")
    public String loginWithGoogle(HttpServletRequest request) {
        String redirectUri = "TODO";
        String clientId = "TODO";
        String googleLoginUrl = "TODO" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri;
        return "redirect:" + googleLoginUrl;
    }



}
