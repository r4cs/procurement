package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropostaDeVendaMapper {
    PropostaDeVendaMapper INSTANCE = Mappers.getMapper(PropostaDeVendaMapper.class);

    PropostaDeVendaDTO entityToDto(PropostaDeVenda propostaDeVenda);

    PropostaDeVenda dtoToEntity(PropostaDeVendaDTO dto);
}
