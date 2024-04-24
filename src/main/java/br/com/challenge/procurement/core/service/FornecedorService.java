package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.Fornecedor;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.service.mapper.FornecedorMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.challenge.procurement.core.model.DTO.FornecedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepo repo;
    private final FornecedorMapperImpl mapper;

    @Autowired
    public FornecedorService( FornecedorRepo repo, FornecedorMapperImpl mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Transactional
    public String create(FornecedorDTO dto) {
        repo.save(mapper.dtoToEntity(dto));
        return "Fornecedor criado com sucesso.";
    }

    public Page<Fornecedor> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public FornecedorDTO getById(Long id) {
        Fornecedor fornecedor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        return mapper.entityToDto(fornecedor);
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
