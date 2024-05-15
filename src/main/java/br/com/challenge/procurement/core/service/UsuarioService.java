package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import br.com.challenge.procurement.core.repositories.UsuarioRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public String create(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto);
        usuarioRepo.save(usuario);
        return "Usuário cadastrado com sucesso";
    }

    public Page<Usuario> list(Pageable pageable) {
        return usuarioRepo.findAll(pageable);
    }


    public List<Usuario> listAll() {return usuarioRepo.findAll();}

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Transactional
    public String update(Long id, Usuario updatedUsuario) {
        Optional<Usuario> endAntigo = usuarioRepo.findById(id);

        if (endAntigo.isPresent()) {
            Usuario usuario = endAntigo.get();
            Optional.ofNullable(updatedUsuario.getNome())
                    .ifPresent(usuario::setNome);
            Optional.ofNullable(updatedUsuario.getEmail())
                    .ifPresent(usuario::setEmail);

            usuarioRepo.save(usuario);
            return "Usuário alterado com sucesso: " + usuario.toString();
        } else {
            // throw  new UsuarioNotFoundException(id);
            System.out.println("criar classe UsuarioNotFoundException" );
            return "Não foi possível alterar usuário, verifique seus dados de entrada.";
        }
    }

    public String delete(Long id) {
        usuarioRepo.deleteById(id);
        return "Usuário excluído.";
    }
}
