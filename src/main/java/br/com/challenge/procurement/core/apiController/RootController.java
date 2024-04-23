package br.com.challenge.procurement.core.apiController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/")
public class RootController {
    @GetMapping(value="/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
