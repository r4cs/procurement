package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import br.com.challenge.procurement.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value="/api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarTodos(
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
        Page<Usuario> usuarios = service.list(defaultPageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuario(@PathVariable Long id){
        return ResponseEntity.ok(service.getUsuarioById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario novaSolicitacao) {
        return ResponseEntity.ok(service.update(id, novaSolicitacao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
