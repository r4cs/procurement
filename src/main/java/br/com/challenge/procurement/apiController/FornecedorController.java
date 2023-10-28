package br.com.challenge.procurement.apiController;


import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.service.FornecedorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/fornecedor")
public class FornecedorController {

    private FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        System.out.println("dados fornecedor: " + fornecedorDTO);
        fornecedorService.criar(fornecedorDTO);
    }

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorService.listaFornecedores();
    }

    @GetMapping(value = "/{id}")
    public Optional<Fornecedor> obterFornecedor(@PathVariable Long id){
        return fornecedorService.getFornecedorById(id);
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarFornecedor(@PathVariable Long id, @RequestBody @Valid Fornecedor novoFornecedor) {
        fornecedorService.updateFornecedor(id, novoFornecedor);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deleteFornecedor(id);
    }
}
