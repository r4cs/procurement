package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.repositories.SolicitacaoDeCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SolicitacaoDeCompraService {

    private final SolicitacaoDeCompraRepo repo;

    @Autowired
    public SolicitacaoDeCompraService(SolicitacaoDeCompraRepo repo) {
        this.repo = repo;
    }


    @Transactional
    public SolicitacaoDeCompra create(SolicitacaoDeCompraDTO dto) {
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra(dto);
        return repo.save(solicitacaoDeCompra);
    }

    public Page<SolicitacaoDeCompra> list(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Optional<SolicitacaoDeCompra> getById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public SolicitacaoDeCompra update(Long id, SolicitacaoDeCompra updatedSolicitacaoDeCompra) {
        Optional<SolicitacaoDeCompra> solicitacaoAntiga = repo.findById(id);
        // só forneceremos update para os seguintes atributos:
        if(solicitacaoAntiga.isPresent()){
            SolicitacaoDeCompra solicitacaoDeCompra = solicitacaoAntiga.get();

            if (updatedSolicitacaoDeCompra.getQuantidade() != null) {
                solicitacaoDeCompra.setQuantidade(updatedSolicitacaoDeCompra.getQuantidade());
            }
            if (updatedSolicitacaoDeCompra.getStatus() != null) {
                solicitacaoDeCompra.setStatus(updatedSolicitacaoDeCompra.getStatus());
            }
            solicitacaoDeCompra.setData_solicitacao(LocalDateTime.now());

            return repo.save(solicitacaoDeCompra);
        } else {
            return null;
        }
    }

    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Solicitação de compra excluída";
    }
}
