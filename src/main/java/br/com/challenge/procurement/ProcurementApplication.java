package br.com.challenge.procurement;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
@SpringBootApplication
//@OpenAPIDefinition(info= @Info(title="Procurement API", version = "0.1", description = "Api que integra solicitações de procurement"))
public class ProcurementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementApplication.class, args);
	}

}

