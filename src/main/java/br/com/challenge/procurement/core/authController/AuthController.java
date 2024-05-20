package br.com.challenge.procurement.core.authController;

import br.com.challenge.procurement.core.model.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/content")
    String index(Principal principal, Model model) {
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
        System.out.println("Principal: " + principal);
        return "home/homeSignedIn";
    }

    @GetMapping("/logout")
    String logout() {
        logger.debug("Acessando a página de logout");
        return "/home/homeNotSignedIn";
    }

}
