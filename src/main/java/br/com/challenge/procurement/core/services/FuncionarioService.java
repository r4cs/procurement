package br.com.challenge.procurement.core.services;

import br.com.challenge.procurement.core.models.DTO.FuncionarioDTO;
import br.com.challenge.procurement.core.models.entities.Funcionario;
import br.com.challenge.procurement.core.repositories.FuncionarioRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepo funcionarioRepo;

    public FuncionarioService(FuncionarioRepo funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    @Transactional
    public String create(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario(dto);
        funcionarioRepo.save(funcionario);
        return "Usuário cadastrado com sucesso";
    }

    public Page<Funcionario> list(Pageable pageable) {
        return funcionarioRepo.findAll(pageable);
    }


    public List<Funcionario> listAll() {return funcionarioRepo.findAll();}

    public Optional<Funcionario> getUsuarioById(Long id) {
        return funcionarioRepo.findById(id);
    }

    @Transactional
    public String update(Long id, Funcionario updatedFuncionario) {
        Optional<Funcionario> endAntigo = funcionarioRepo.findById(id);

        if (endAntigo.isPresent()) {
            Funcionario funcionario = endAntigo.get();
            Optional.ofNullable(updatedFuncionario.getNome())
                    .ifPresent(funcionario::setNome);
            Optional.ofNullable(updatedFuncionario.getEmail())
                    .ifPresent(funcionario::setEmail);

            funcionarioRepo.save(funcionario);
            return "Usuário alterado com sucesso: " + funcionario.toString();
        } else {
            // throw  new UsuarioNotFoundException(id);
            System.out.println("criar classe UsuarioNotFoundException" );
            return "Não foi possível alterar usuário, verifique seus dados de entrada.";
        }
    }

    public String delete(Long id) {
        funcionarioRepo.deleteById(id);
        return "Usuário excluído.";
    }
}
