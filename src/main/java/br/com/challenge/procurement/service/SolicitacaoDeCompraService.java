package br.com.challenge.procurement.service;

import br.com.challenge.procurement.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.model.SolicitacaoDeCompra;
import br.com.challenge.procurement.repositories.SolicitacaoDeCompraRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(solicitacaoAntiga.isPresent()){
            SolicitacaoDeCompra solicitacaoDeCompra = solicitacaoAntiga.get();

            solicitacaoDeCompra.setSku(updatedSolicitacaoDeCompra.getSku());
            solicitacaoDeCompra.setQtde(updatedSolicitacaoDeCompra.getQtde());
            solicitacaoDeCompra.setValor_unitario(updatedSolicitacaoDeCompra.getValor_unitario());
            solicitacaoDeCompra.setSolicitante(updatedSolicitacaoDeCompra.getSolicitante());
            solicitacaoDeCompra.setAprovador(updatedSolicitacaoDeCompra.getAprovador());
            solicitacaoDeCompra.setStatus(updatedSolicitacaoDeCompra.getStatus());
            solicitacaoDeCompra.setMotivo_recusado(updatedSolicitacaoDeCompra.getMotivo_recusado());
            solicitacaoDeCompra.setData_solicitacao(updatedSolicitacaoDeCompra.getData_solicitacao());

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
