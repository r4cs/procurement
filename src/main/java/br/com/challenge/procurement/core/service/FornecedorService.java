package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.Fornecedor;
import br.com.challenge.procurement.core.repositories.FornecedorRepo;
import br.com.challenge.procurement.core.entities.DTO.FornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepo fornecedorRepo;

    public FornecedorService(@Autowired FornecedorRepo fornecedorRepo) {
        this.fornecedorRepo = fornecedorRepo;
    }

    public void criar(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor(dto);
        fornecedorRepo.save(fornecedor);
    }

    public List<Fornecedor> listaFornecedores() {
        return fornecedorRepo.findAll();
    }

    public Optional<Fornecedor> getFornecedorById(Long id) {
        return fornecedorRepo.findById(id);
    }

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

    public void deleteFornecedor(Long id) {
        fornecedorRepo.deleteById(id);
    }
}
