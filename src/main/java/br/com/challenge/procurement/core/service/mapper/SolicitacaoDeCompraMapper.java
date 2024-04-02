package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.entities.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;

public class SolicitacaoDeCompraMapper {

    SolicitacaoDeCompra toEntity(SolicitacaoDeCompraDTO dto) {
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra();
        solicitacaoDeCompra.setId(dto.id());
        solicitacaoDeCompra.setQuantidade(dto.quantidade());
        return solicitacaoDeCompra;
    };

    SolicitacaoDeCompraDTO toDTO(SolicitacaoDeCompra solicitacaoDeCompra) {
        return new SolicitacaoDeCompraDTO(solicitacaoDeCompra);
    }

}
