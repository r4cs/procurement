package br.com.challenge.procurement;

import br.com.challenge.procurement.core.model.DTO.*;
import br.com.challenge.procurement.core.model.entities.*;
import br.com.challenge.procurement.core.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
public class PropostaDeVendaControllerTest {


    @MockBean
    private PropostaDeVendaService propostaDeVendaService;

    @MockBean
    private SolicitacaoDeCompraService solicitacaoDeCompraService;


    @MockBean
    private PedidoDeCompraService pedidoDeCompraService;

    @MockBean
    private FornecedorService fornecedorService;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private UsuarioService usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MockMvc mockMvc;

    private final Faker faker = new Faker();

    @Autowired
    public PropostaDeVendaControllerTest(MockMvc mockMvc, PropostaDeVendaService propostaDeVendaService) {
        this.mockMvc = mockMvc;
        this.propostaDeVendaService=propostaDeVendaService;
        // Foi necessário configurar o Jackson (pasta config) para serialização/desserialização do LocalDateTime
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
    }

    @Test
    void criarPropostaDeVendaComSucesso() throws Exception {

        EnderecoDTO enderecoDTO = new EnderecoDTO(
                faker.address().streetName(),
                faker.address().buildingNumber(),
                null,
                faker.address().streetName(),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().zipCode()
        );
        FornecedorDTO fornecedorDto = new FornecedorDTO(
                faker.company().name(),
                faker.company().bs(),
                faker.zelda().character(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),
                enderecoDTO
        );
        Fornecedor fornecedor = new Fornecedor(fornecedorDto);

        ProdutoDTO produtoDTO = new ProdutoDTO(
                null,
                faker.commerce().productName(),
                faker.commerce().productName(),
                faker.company().name(),
                faker.commerce().color()
        );
        Produto produto = new Produto(produtoDTO);

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                faker.name().fullName(),
                faker.internet().password(),
                faker.internet().emailAddress()
        );
        Usuario usuario = new Usuario(usuarioDTO);


        SolicitacaoDeCompraDTO solicitacaoDeCompraDTO = new SolicitacaoDeCompraDTO(
                produto,
                faker.number().numberBetween(1, 100),
                usuario,
                Status.PENDING,
                null
        );
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra(solicitacaoDeCompraDTO);

        PedidoDeCompraDTO pedidoDeCompraDTO = new PedidoDeCompraDTO(
                solicitacaoDeCompra,
                TipoDePagamento.PIX,
                LocalDateTime.now()
        );

        PropostaDeVendaDTO propostaDeVendaDTO = new PropostaDeVendaDTO(
                null,
                solicitacaoDeCompra,
                BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100)),
                null,
                fornecedor);

        PropostaDeVenda propostaDeVenda = new PropostaDeVenda(propostaDeVendaDTO);

        Mockito.when(fornecedorService.create(any(FornecedorDTO.class)))
                .thenReturn(String.valueOf("Fornecedor criado com sucesso."));

        when(produtoService.create(any(ProdutoDTO.class)))
                .thenReturn(produto);

        when(usuarioService.create(any(UsuarioDTO.class)))
                .thenReturn("Usuário cadastrado com sucesso");

        when(solicitacaoDeCompraService.create(any(SolicitacaoDeCompraDTO.class)))
                .thenReturn(solicitacaoDeCompra);

        when(pedidoDeCompraService.create(any(PedidoDeCompraDTO.class)))
                .thenReturn("Pedido de compra criado com sucesso.");

        when(propostaDeVendaService.create(any(PropostaDeVendaDTO.class)))
                .thenReturn(propostaDeVenda);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/proposta-fornecedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propostaDeVendaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
