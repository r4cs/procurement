package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepo repo;

    @Autowired
    public FornecedorService( FornecedorRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public String create(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor(dto);
        repo.save(fornecedor);
        return "Fornecedor criado com sucesso.";
    }

    public Page<Fornecedor> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<Fornecedor> listAll() { return repo.findAll();}

    public Optional<Fornecedor> getById(Long id) {
        return repo.findById(id);
    }


    @Transactional
    public String update(Long id, Fornecedor updatedFornecedor) {
        Optional<Fornecedor> fornecedorAntigo = repo.findById(id);

        if (fornecedorAntigo.isPresent()) {
            Fornecedor fornecedor = fornecedorAntigo.get();
            Optional.ofNullable(updatedFornecedor.getRazao_social())
                    .ifPresent(fornecedor::setRazao_social);
            Optional.ofNullable(updatedFornecedor.getCnpj())
                    .ifPresent(fornecedor::setCnpj);
            Optional.ofNullable(updatedFornecedor.getNome_contato())
                    .ifPresent(fornecedor::setNome_contato);
            Optional.ofNullable(updatedFornecedor.getTelefone())
                    .ifPresent(fornecedor::setTelefone);
            Optional.ofNullable(updatedFornecedor.getEmail())
                    .ifPresent(fornecedor::setEmail);
            repo.save(fornecedor);
            return "Fornecedor alterado com sucesso";
        } else {
            return "Algo deu errado, verifique os dados inseridos";
        }
    }

    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Forncededor de id {%s} excluído.".formatted(id);
    }
}
