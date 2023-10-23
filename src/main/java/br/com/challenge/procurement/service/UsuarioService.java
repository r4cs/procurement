package br.com.challenge.procurement.service;

import br.com.challenge.procurement.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.model.Usuario;
import br.com.challenge.procurement.repositories.UsuarioRepo;
import br.com.challenge.procurement.repositories.UsuarioRepo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public void create(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto);
        usuarioRepo.save(usuario);
    }

    public List<Usuario> list() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepo.findById(id);
    }

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

    public void delete(Long id) {
        usuarioRepo.deleteById(id);
    }
}
