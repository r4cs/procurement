package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.core.entities.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.entities.Usuario;
import br.com.challenge.procurement.core.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        out.println("Dados solicitacao de compra: " + dto);
        usuarioService.create(dto);
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.list();
    }

    @GetMapping(value = "/{id}")
    public Optional<Usuario> obterUsuario(@PathVariable Long id){
        return usuarioService.getUsuarioById(id);
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario novaSolicitacao) {
        usuarioService.update(id, novaSolicitacao);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
    }

}
