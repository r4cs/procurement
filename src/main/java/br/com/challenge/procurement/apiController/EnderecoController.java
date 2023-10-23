package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.model.Endereco;
import br.com.challenge.procurement.service.EnderecoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/endereco") // , produces = "application/json")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid EnderecoDTO dados) {
        System.out.println("dados endereco: " + dados);
        enderecoService.create(dados);
    }

    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.list();
    }

    @GetMapping(value = "/{id}")
    public Optional<Endereco> obterEndereco(@PathVariable Long id) {
        return enderecoService.getEnderecoById(id);
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarEndereco(@PathVariable Long id, @RequestBody @Valid Endereco novoEndereco) {
        enderecoService.update(id, novoEndereco);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarEndereco(@PathVariable Long id) {
        enderecoService.delete(id);
    }


}
