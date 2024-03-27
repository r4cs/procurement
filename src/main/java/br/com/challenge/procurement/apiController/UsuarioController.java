package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.entities.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.entities.Usuario;
import br.com.challenge.procurement.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;


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

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.list(pageable));
    }

//    @GetMapping
//    public ResponseEntity<Page<Usuario>> listarTodos(
//            @PageableDefault(sort = "campo_desejado") Pageable pageable,
//            @Parameter(description = "Ordenação dos resultados") @SortDefault.SortDefaults({
//                    @SortDefault(sort = "campo_desejado", direction = Sort.Direction.ASC),
//                    @SortDefault(sort = "campo_secundario", direction = Sort.Direction.DESC)
//            }) Sort sort) {
//        return ResponseEntity.ok(usuarioService.list(pageable));
//    }

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
