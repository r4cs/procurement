package br.com.challenge.procurement.core.authController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/")
    String index(Principal principal) {
        System.out.println("Principal em root /: " + principal);
        return principal != null ? "/home/homeSignedIn" : "/home/homeNotSignedIn";
    }

    @GetMapping("/login")
    String login() {
        return "/home/homeNotSignedIn";
    }

    @GetMapping("/logout")
    String logout() {
        return "/home/homeNotSignedIn";
    }
}
