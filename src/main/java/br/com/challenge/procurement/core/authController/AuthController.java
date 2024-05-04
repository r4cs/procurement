package br.com.challenge.procurement.core.authController;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.model.entities.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AuthController {

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