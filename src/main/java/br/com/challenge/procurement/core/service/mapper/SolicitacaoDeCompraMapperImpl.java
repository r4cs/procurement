package br.com.challenge.procurement.core.service.mapper;

import br.com.challenge.procurement.core.model.DTO.SolicitacaoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.SolicitacaoDeCompra;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoDeCompraMapperImpl implements SolicitacaoDeCompraMapper{

    @Override
    public SolicitacaoDeCompraDTO entityToDto(SolicitacaoDeCompra solicitacaoDeCompra) {
        if (solicitacaoDeCompra == null) {
                return null;
        }

        return new SolicitacaoDeCompraDTO(
                solicitacaoDeCompra.getProduto(),
                solicitacaoDeCompra.getQuantidade(),
                solicitacaoDeCompra.getSolicitante(),
                solicitacaoDeCompra.getStatus(),
                solicitacaoDeCompra.getData_solicitacao()
        );
    }

    @Override
    public SolicitacaoDeCompra dtoToEntity(SolicitacaoDeCompraDTO dto) {
        if (dto == null) {
            return null;
        }
        return new SolicitacaoDeCompra(dto);
    }
}
