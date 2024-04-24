package br.com.challenge.procurement.core.apiController;


import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.service.FornecedorService;
import br.com.challenge.procurement.core.service.mapper.FornecedorMapperImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/fornecedor")
public class FornecedorController {

    private final FornecedorService service;
    private final FornecedorMapperImpl mapper;

    @Autowired
    public FornecedorController(FornecedorService service, FornecedorMapperImpl mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid FornecedorDTO fornecedorDTO) {
        System.out.println("dados fornecedor: " + fornecedorDTO);
        return ResponseEntity.ok(service.create(fornecedorDTO));
    }


    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<Fornecedor> fornecedores = service.list(defaultPageable);
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> obterFornecedor(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> atualizarFornecedor(
            @PathVariable Long id,
            @RequestBody @Valid Fornecedor novoFornecedor) {
        return ResponseEntity.ok(service.update(id, novoFornecedor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarFornecedor(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
