package br.com.challenge.procurement.core.authController;

//import jakarta.servlet.http.HttpServletRequest;
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

}
