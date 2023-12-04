package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.entities.Usuario;
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
    public Usuario create(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto);
        return usuarioRepo.save(usuario);
    }

    public Page<Usuario> list(Pageable pageable) {
        return usuarioRepo.findAll(pageable);
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Transactional
    public Usuario update(Long id, Usuario updatedUsuario) {
        Optional<Usuario> endAntigo = usuarioRepo.findById(id);

        if (endAntigo.isPresent()) {
            Usuario usuario = endAntigo.get();

            usuario.setNome(updatedUsuario.getNome());
            usuario.setEmail(updatedUsuario.getEmail());
            usuario.setSenha(updatedUsuario.getSenha());
            return usuarioRepo.save(usuario);
        } else {
            // throw  new UsuarioNotFoundException(id);
            System.out.println("criar classe UsuarioNotFoundException" );
            return null;
        }
    }

    public String delete(Long id) {
        usuarioRepo.deleteById(id);
        return "Usuário excluído.";
    }
}
