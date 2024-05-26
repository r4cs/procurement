package br.com.challenge.procurement.core.services;

import br.com.challenge.procurement.core.models.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.models.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.repositories.SolicitacaoDeCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoDeCompraService {

    private final SolicitacaoDeCompraRepo solicitacaoDeCompraRepo;

    @Autowired
    public SolicitacaoDeCompraService(SolicitacaoDeCompraRepo solicitacaoDeCompraRepo) {
        this.solicitacaoDeCompraRepo = solicitacaoDeCompraRepo;
    }


    @Transactional
    public SolicitacaoDeCompra create(SolicitacaoDeCompraDTO dto) {
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra(dto);
        return solicitacaoDeCompraRepo.save(solicitacaoDeCompra);
    }

    public Page<SolicitacaoDeCompra> list(Pageable pageable){
        return solicitacaoDeCompraRepo.findAll(pageable);
    }


    public List<SolicitacaoDeCompra> listAll() {return solicitacaoDeCompraRepo.findAll();}

    public Optional<SolicitacaoDeCompra> getSolicitacaoDeCompraById(Long id) {
        return solicitacaoDeCompraRepo.findById(id);
    }

    @Transactional
    public SolicitacaoDeCompra update(Long id, SolicitacaoDeCompra updatedSolicitacaoDeCompra) {
        Optional<SolicitacaoDeCompra> solicitacaoAntiga = solicitacaoDeCompraRepo.findById(id);
        // só forneceramos update para os seguintes atributos:
        if(solicitacaoAntiga.isPresent()){
            SolicitacaoDeCompra solicitacaoDeCompra = solicitacaoAntiga.get();

            if (updatedSolicitacaoDeCompra.getQuantidade() != null) {
                solicitacaoDeCompra.setQuantidade(updatedSolicitacaoDeCompra.getQuantidade());
            }
            if (updatedSolicitacaoDeCompra.getStatus() != null) {
                solicitacaoDeCompra.setStatus(updatedSolicitacaoDeCompra.getStatus());
            }
            solicitacaoDeCompra.setData_solicitacao(LocalDateTime.now());

            return solicitacaoDeCompraRepo.save(solicitacaoDeCompra);
        } else {
            System.out.println("criar classe SolicitacaoDeCompraNotFoundException" );
            return null;
        }
    }

    @Transactional
    public String delete(Long id) {
        solicitacaoDeCompraRepo.deleteById(id);
        return "Solicitação de compra excluída";
    }
}
