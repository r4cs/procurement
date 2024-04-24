package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.DTO.UsuarioDTO;
import br.com.challenge.procurement.core.model.entities.Usuario;
import br.com.challenge.procurement.core.repositories.UsuarioRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepo repo;

    public UsuarioService(UsuarioRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public String create(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto);
        repo.save(usuario);
        return "Usuário cadastrado com sucesso";
    }

    public Page<Usuario> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<Usuario> getById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public String update(Long id, Usuario updatedUsuario) {
        Optional<Usuario> endAntigo = repo.findById(id);

        if (endAntigo.isPresent()) {
            Usuario usuario = endAntigo.get();
            Optional.ofNullable(updatedUsuario.getNome())
                    .ifPresent(usuario::setNome);
            Optional.ofNullable(updatedUsuario.getEmail())
                    .ifPresent(usuario::setEmail);
            Optional.ofNullable(updatedUsuario.getSenha())
                    .ifPresent(usuario::setSenha);
            repo.save(usuario);
            return "Usuário alterado com sucesso: " + usuario.toString();
        } else {
            // throw  new UsuarioNotFoundException(id);
            System.out.println("criar classe UsuarioNotFoundException" );
            return "Não foi possível alterar usuário, verifique seus dados de entrada.";
        }
    }

    public String delete(Long id) {
        repo.deleteById(id);
        return "Usuário excluído.";
    }
}
