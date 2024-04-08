package br.com.challenge.procurement;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestarApiSprint3EnterpriseDtoNet {

    private static Faker faker = new Faker();


    @Test
    @Order(1)
    public void testarCriarUsuario() throws URISyntaxException {
        final String baseUrl = "http://localhost:5028/User/register";
        URI uri = new URI(baseUrl);
        JsonObject user = new JsonObject();
        user.addProperty("name", faker.zelda().character());
        user.addProperty("email", faker.internet().emailAddress());
        user.addProperty("password", faker.beer().style());

        // Configurando o cabeçalho com Content-Type: application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Criando uma entidade HttpEntity com o JSON e o cabeçalho
        HttpEntity<String> requestEntity = new HttpEntity<>(user.toString(), headers);

        // Enviando a solicitação POST com o JSON e o cabeçalho para o endpoint da API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, requestEntity, String.class);

        // Verificando se a resposta não é nula
        assertNotNull(responseEntity.getBody());

        // Verificando se a resposta contém uma mensagem de sucesso
        assertEquals("Teste concluído: ", HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(2)
    public void testarLoginUsuario() throws JSONException {
        final String baseUrl = "http://localhost:5028/User/login";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("email", "aaaaa@example.com")
                .queryParam("password", "string");

        // Configurando os cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.add("email", "aaaaa@example.com");
        headers.add("password", "string");

        // Criando uma entidade HttpEntity com os cabeçalhos
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Enviando a solicitação GET para o endpoint da API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.toUriString(), String.class, requestEntity);

        // Verificando se a resposta não é nula
        assertNotNull(responseEntity.getBody());

        // Verificando se a resposta contém o email e a senha
        JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
        String email = jsonResponse.getString("email");
        String password = jsonResponse.getString("password");

        // Verificando se o email e a senha correspondem ao esperado
        assertEquals("Teste concluído com sucesso", "email: aaaaa@example.com, password: string", "email: " + email + ", password: " + password);
    }
}
