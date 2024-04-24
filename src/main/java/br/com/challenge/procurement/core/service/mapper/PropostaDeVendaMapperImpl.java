package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import org.springframework.stereotype.Component;

@Component
public class PropostaDeVendaMapperImpl implements PropostaDeVendaMapper {

    @Override
    public PropostaDeVendaDTO entityToDto(PropostaDeVenda propostaDeVenda) {
        if (propostaDeVenda == null ) {
            return null;
        }

        return new PropostaDeVendaDTO(
                propostaDeVenda.getId(),
                propostaDeVenda.getPedido_compra(),
                propostaDeVenda.getValor_unitario(),
                propostaDeVenda.getValor_total(),
                propostaDeVenda.getFornecedor()
        );
    }

    @Override
    public PropostaDeVenda dtoToEntity(PropostaDeVendaDTO dto) {
        if (dto == null) {
            return null;
        }
        return new PropostaDeVenda(dto);
    }
}
