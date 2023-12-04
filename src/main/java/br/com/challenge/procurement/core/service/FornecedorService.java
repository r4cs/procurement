package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepo fornecedorRepo;

    @Autowired
    public FornecedorService( FornecedorRepo fornecedorRepo) {
        this.fornecedorRepo = fornecedorRepo;
    }


    @Transactional
    public Fornecedor criar(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor(dto);
        fornecedorRepo.save(fornecedor);
        return fornecedor;
    }

    public Page<Fornecedor> listaFornecedores(Pageable pageable) {
        return fornecedorRepo.findAll(pageable);
    }

    public Optional<Fornecedor> getFornecedorById(Long id) {
        return fornecedorRepo.findById(id);
    }


    @Transactional
    public Fornecedor updateFornecedor(Long id, Fornecedor updatedFornecedor) {
        Optional<Fornecedor> endAntigo = fornecedorRepo.findById(id);

        if (endAntigo.isPresent()) {
            Fornecedor fornecedor = endAntigo.get();
            fornecedor.setRazao_social(updatedFornecedor.getRazao_social());
            fornecedor.setCnpj(updatedFornecedor.getCnpj());
            fornecedor.setTelefone(updatedFornecedor.getTelefone());
            fornecedor.setEmail(updatedFornecedor.getEmail());
            return fornecedorRepo.save(fornecedor);
        } else {
            System.out.println("criar classe FornecedorNotFoundException" );
            return null;
        }
    }

    public String deleteFornecedor(Long id) {
        fornecedorRepo.deleteById(id);
        return "Forncededor de id {%s} excluído.".formatted(id);
    }
}
