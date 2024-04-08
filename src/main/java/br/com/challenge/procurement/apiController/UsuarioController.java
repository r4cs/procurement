package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.model.entities.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import br.com.challenge.procurement.core.service.UsuarioService;
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

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        System.out.println("Dados solicitacao de compra: " + dto);
        return ResponseEntity.ok(usuarioService.create(dto));
    }

//    @GetMapping
//    public ResponseEntity<Page<Usuario>> listarTodos(Pageable pageable) {
//        return ResponseEntity.ok(usuarioService.list(pageable));
//    }
    @GetMapping
    public ResponseEntity<Page<Usuario>> listarTodos(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by("id")
        );
        Page<Usuario> usuarios = usuarioService.list(defaultPageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario novaSolicitacao) {
        return ResponseEntity.ok(usuarioService.update(id, novaSolicitacao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}
