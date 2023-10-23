package br.com.challenge.procurement.service;

import br.com.challenge.procurement.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.model.Endereco;
import br.com.challenge.procurement.repositories.EnderecoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepo enderecoRepo;

    public EnderecoService(EnderecoRepo enderecoRepo) {
        this.enderecoRepo = enderecoRepo;
    }

    public void create(EnderecoDTO dto) {
        Endereco endereco = new Endereco(dto);
        enderecoRepo.save(endereco);
    }

    public List<Endereco> list() {
        return enderecoRepo.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return enderecoRepo.findById(id);
    }

    public Endereco update(Long id, Endereco updatedEndereco) {
        Optional<Endereco> endAntigo = enderecoRepo.findById(id);

        if (endAntigo.isPresent()) {
            Endereco endereco = endAntigo.get();

            endereco.setLogradouro(updatedEndereco.getLogradouro());
            endereco.setNumero(updatedEndereco.getNumero());
            endereco.setCidade(updatedEndereco.getCidade());
            endereco.setEstado(updatedEndereco.getEstado());
            endereco.setCep(updatedEndereco.getCep());

            return enderecoRepo.save(endereco);
        } else {
            // throw  new EnderecoNotFoundException(id);
            System.out.println("criar classe EnderecoNotFoundException" );
            return null;
        }
    }

    public void delete(Long id) {
        enderecoRepo.deleteById(id);
    }

}









