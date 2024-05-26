package br.com.challenge.procurement.core.controllers.apiController;

import br.com.challenge.procurement.core.models.DTO.FuncionarioDTO;
import br.com.challenge.procurement.core.models.entities.Funcionario;
import br.com.challenge.procurement.core.repositories.FuncionarioRepo;
import br.com.challenge.procurement.core.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/api/usuario")
public class FuncionarioController {

    private final FuncionarioService service;
    private final FuncionarioRepo funcionarioRepo;

    @Autowired
    public FuncionarioController(FuncionarioService service,
                                 FuncionarioRepo funcionarioRepo) {
        this.service = service;
        this.funcionarioRepo = funcionarioRepo;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid FuncionarioDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<Funcionario>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, nome, email")
            @RequestParam(required = false, defaultValue = "id") String orderBy
            ) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by(orderBy)
        );
        Page<Funcionario> usuarios = service.list(defaultPageable);
        return ResponseEntity.ok(usuarios);
    }


    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Funcionario>> obterUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.getUsuarioById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Funcionario novaSolicitacao) {
        return ResponseEntity.ok(service.update(id, novaSolicitacao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
