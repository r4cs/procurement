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

//    @GetMapping("/login")
//    String login() {
//        return "/home/homeNotSignedIn";
//    }

    @GetMapping("/logout")
    String logout() {
        return "/home/homeNotSignedIn";
    }

    @GetMapping("/login/github")
    public String loginWithGithub(HttpServletRequest request) {
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
