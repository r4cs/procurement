package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;
import br.com.challenge.procurement.core.repositories.SolicitacaoDeCompraRepo;
import br.com.challenge.procurement.core.entities.DTO.SolicitacaoDeCompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(SolicitacaoDeCompraDTO dto) {
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra(dto);
        solicitacaoDeCompraRepo.save(solicitacaoDeCompra);
    }

    public List<SolicitacaoDeCompra> list(){
        return solicitacaoDeCompraRepo.findAll();
    }

    public Optional<SolicitacaoDeCompra> getSolicitacaoDeCompraById(Long id) {
        return solicitacaoDeCompraRepo.findById(id);
    }

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
            if (updatedSolicitacaoDeCompra.getMotivo_recusado() != null) {
                solicitacaoDeCompra.setMotivo_recusado(updatedSolicitacaoDeCompra.getMotivo_recusado());
            }
            solicitacaoDeCompra.setData_solicitacao(LocalDateTime.now());

            return solicitacaoDeCompraRepo.save(solicitacaoDeCompra);
        } else {
            System.out.println("criar classe SolicitacaoDeCompraNotFoundException" );
            return null;
        }
    }

    public void delete(Long id) {
        solicitacaoDeCompraRepo.deleteById(id);
    }

}
