package br.com.challenge.procurement.core.controllers.authController;

import br.com.challenge.procurement.core.models.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @GetMapping("/")
    String index(Principal principal, Model model) {
        System.out.println("Principal em root /: " + principal);
        List<Class<?>> entidades = Arrays.asList(
                Fornecedor.class,
                PedidoDeCompra.class,
                Produto.class,
                PropostaDeVenda.class,
                SolicitacaoDeCompra.class,
                Funcionario.class);
        model.addAttribute("entidades", entidades);
        List<String> endpoints = entidades.stream()
                .map(e -> e.getSimpleName().split("(?=[A-Z])")[0])
                .collect(Collectors.toList());
        model.addAttribute("endpoints", endpoints);

        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @GetMapping("/logout")
    String logout() {
        return "/home/homeNotSignedIn";
    }

}