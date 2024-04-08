package br.com.challenge.procurement;

import br.com.challenge.procurement.core.apiController.FornecedorController;
import br.com.challenge.procurement.core.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.service.FornecedorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(FornecedorController.class)
public class FornecedorControllerComponentTest {



    @MockBean
    private FornecedorService service;

    private MockMvc mockMvc;
    private final Faker faker = new Faker();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public FornecedorControllerComponentTest(MockMvc mockMvc,
                                             FornecedorService service) {
        this.mockMvc = mockMvc;
        this.service = service;
    }

    @Test
    void createFornecedorComSucesso() throws Exception{
        EnderecoDTO endereco = new EnderecoDTO(
                faker.address().streetName(),
                faker.address().streetAddressNumber(),
                faker.ancient().primordial(),
                faker.ancient().god(),
                faker.address().cityName(),
                faker.address().stateAbbr(),
                faker.address().zipCode()
        );
        FornecedorDTO fornecedorDto = new FornecedorDTO(
                faker.company().name(),
                faker.company().bs(),
                faker.zelda().character(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),
                endereco
        );

        String jsonRequest = objectMapper.writeValueAsString(fornecedorDto);

        Mockito.when(service.criar(any(FornecedorDTO.class))).thenReturn(String.valueOf("Fornecedor criado com sucesso."));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/fornecedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void createFornecedorSemSucesso() throws JsonProcessingException, Exception {
    EnderecoDTO endereco = new EnderecoDTO(
            faker.address().streetName(),
            faker.address().streetAddressNumber(),
            faker.ancient().primordial(),
            faker.ancient().god(),
            faker.address().cityName(),
            faker.address().stateAbbr(),
            faker.address().zipCode()
    );
    FornecedorDTO fornecedorDto = new FornecedorDTO(
            faker.company().name(),
            null,
            faker.zelda().character(),
            faker.phoneNumber().cellPhone(),
            faker.internet().emailAddress(),
            endereco
    );

    String jsonRequest = objectMapper.writeValueAsString(fornecedorDto);

    // obs: o bad request vem da classe GlobalExceptionHandler
    mockMvc.perform(MockMvcRequestBuilders.post("/api/fornecedor")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
