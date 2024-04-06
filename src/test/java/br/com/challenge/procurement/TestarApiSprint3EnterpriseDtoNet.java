package br.com.challenge.procurement;

import br.com.challenge.procurement.core.model.entities.Usuario;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestarApiSprint3EnterpriseDtoNet {

    @Autowired
    private TestRestTemplate restTemplate;

    private static Faker faker = new Faker();


    @Test
    @Order(1)
    public void testarCriarUsuario() throws URISyntaxException {
        final String baseUrl = "http://localhost:5028/user/register";
        URI uri = new URI(baseUrl);

        // Criar um objeto de usuário para enviar como JSON
        Usuario user = new Usuario();
        user.setId(null);
        user.setNome(faker.zelda().character());
        user.setEmail(faker.internet().emailAddress());
        user.setSenha(faker.beer().style());
        System.out.println("User de teste: " + user);

        // Converter o objeto de usuário para JSON usando a biblioteca GSON
        Gson gson = new Gson();
        String json = gson.toJson(user);

        System.out.println("User gson de teste: " + user);

        // Enviar a solicitação POST com o JSON para o endpoint da API
        String response = restTemplate.postForObject(uri, json, String.class);

        // Verificar se a resposta não é nula
        assertNotNull(response);

        // Verificar se a resposta contém uma mensagem de sucesso
        assertEquals("Teste concluído com sucesso","Usuário criado com sucesso", response);
    }
}
