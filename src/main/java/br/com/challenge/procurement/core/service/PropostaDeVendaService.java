package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.repositories.PropostaDeVendaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropostaDeVendaService {

    private final PropostaDeVendaRepo repo;

    @Autowired
    public PropostaDeVendaService(PropostaDeVendaRepo repo) {this.repo = repo;};

    @Transactional
    public PropostaDeVenda criar(PropostaDeVendaDTO dto) {
        PropostaDeVenda propostaDeVenda = new PropostaDeVenda(dto);
        repo.save(propostaDeVenda);
        return propostaDeVenda;
    }

    public Page<PropostaDeVenda> listar(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<PropostaDeVenda> getById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Optional<PropostaDeVenda> update(Long id, PropostaDeVenda novoPropostaDeVenda) {
        Optional<PropostaDeVenda> antigaEnviarProposta = repo.findById(id);

        if (antigaEnviarProposta.isPresent()) {
            PropostaDeVenda propostaDeVendaAtualizada = new PropostaDeVenda();
            propostaDeVendaAtualizada.setSolicitacao_compra(novoPropostaDeVenda.getSolicitacao_compra());
            propostaDeVendaAtualizada.setValor_unitario(novoPropostaDeVenda.getValor_unitario());
            propostaDeVendaAtualizada.setValor_total(novoPropostaDeVenda.getValor_total());
            return Optional.of(repo.save(propostaDeVendaAtualizada));
        } else {
            System.out.println("Nao encontrado");
            return Optional.empty();
        }
    }
    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Proposta de venda de id {%s} atualizado.".formatted(id);
    }
}
