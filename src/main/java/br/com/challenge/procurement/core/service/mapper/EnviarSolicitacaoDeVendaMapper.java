package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.entities.PropostaDeVenda;

public class EnviarSolicitacaoDeVendaMapper {
    public PropostaDeVenda toEntity(PropostaDeVendaDTO dto) {
        PropostaDeVenda propostaDeVenda = new PropostaDeVenda();
        return propostaDeVenda;
    }

    public PropostaDeVendaDTO toDTO(PropostaDeVenda propostaDeVenda) {
        return new PropostaDeVendaDTO(propostaDeVenda);
    }
}
