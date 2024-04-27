package br.com.challenge.procurement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig  implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home/homeNotSignedIn");
        registry.addViewController("/login").setViewName("home/homeNotSignedIn");
        registry.addViewController("/logout").setViewName("home/homeSignedIn");
    }
}