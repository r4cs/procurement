package br.com.challenge.procurement.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {}
//
//    @Bean
//    public Docket procurementApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("br.com.challenge.procurement.apiController"))
//                .paths(regex("/api/.*"))
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    private ApiInfo metaInfo() {
//        ApiInfo apiInfo = new ApiInfo(
//                "Procurement API REST",
//                "Conecta informações relacionadas as etapas do procurement e retorna em uma API",
//                "0.1",
//                "Terms of Service",
//                new Contact("Raquel Guzansky", "https://github.com/r4cs", "r.guzansky@hotmail.com"),
//                "Apache License Version 2.0",
//                "https://www.apache.org/licensen.html", new ArrayList<VendorExtension>()
//        );
//        return apiInfo;
//    }
//}
