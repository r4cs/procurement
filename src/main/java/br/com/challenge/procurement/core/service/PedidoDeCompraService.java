package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.repositories.PedidoDeCompraRepo;
import br.com.challenge.procurement.core.service.strategy.PagamentoBoletoPadrao;
import br.com.challenge.procurement.core.service.strategy.PagamentoCartaoPadrao;
import br.com.challenge.procurement.core.service.strategy.PagamentoPixPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoDeCompraService {
    private final PedidoDeCompraRepo repo;
    private final PagamentoPixPadrao pagamentoPix;
    private final PagamentoBoletoPadrao pagamentoBoleto;
    private final PagamentoCartaoPadrao pagamentoCartao;
    @Autowired
    public PedidoDeCompraService(PedidoDeCompraRepo repo,
                                 PagamentoPixPadrao pagamentoPix,
                                 PagamentoBoletoPadrao pagamentoBoleto,
                                 PagamentoCartaoPadrao pagamentoCartao) {
        this.repo = repo;
        this.pagamentoPix = pagamentoPix;
        this.pagamentoBoleto = pagamentoBoleto;
        this.pagamentoCartao = pagamentoCartao;
    }



    @Transactional
    public String create(PedidoDeCompraDTO dto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(dto);

        repo.save(pedidoDeCompra);
        return "Pedido de compra criado com sucesso.";
    }

    public Page<PedidoDeCompra> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<PedidoDeCompra> getById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public String update(Long id, PedidoDeCompra updatedPedidoDeCompra) {
        Optional<PedidoDeCompra> pedDeCompAntigo = repo.findById(id);
        System.out.println("json do pedido de compra atualizado: "+ updatedPedidoDeCompra);

        if (pedDeCompAntigo.isPresent()) {
            PedidoDeCompra pedidoDeCompra = pedDeCompAntigo.get();
            Optional.ofNullable(updatedPedidoDeCompra.getSolicitacao())
                    .ifPresent(pedidoDeCompra::setSolicitacao);
            pedidoDeCompra.setData_pedido(LocalDateTime.now());
            repo.save(pedidoDeCompra);
            return "Pedido de compra alterado com sucesso";
        } else {
            return "Algo deu errado, verifique os dados inseridos.";
        }
    }

    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Pedido de compra excluído";
    }

    public void processPayment(PedidoDeCompraDTO pedidoDeCompraDTO, PropostaDeVendaDTO propostaDeVendaDTO) {
        // !!!!!!
        // deverá haver uma tela de preenchimento desses dados, por hora teremos um input mockado
        // !!!!!!
        switch (pedidoDeCompraDTO.tipoDePagamento()) {
            case PIX: // valor total e chave pix (email) como parametros
                pagamentoPix.processarPagamentoPix(
                        propostaDeVendaDTO.valor_total(),
                        propostaDeVendaDTO.fornecedor().getEmail()
                );
                break;
            case CARTAO:
                pagamentoCartao.processarPagamentoCartao(
                        propostaDeVendaDTO.valor_total(),
                        "credito",
                        "0000 0000 0000 0000",
                        pedidoDeCompraDTO.solicitacao_id().getSolicitante().getNome(),
                        "07/2033",
                        "499");
                break;
            case BOLETO:
                pagamentoBoleto.gerarBoleto(
                        propostaDeVendaDTO.valor_total(),
                        pedidoDeCompraDTO.solicitacao_id().getSolicitante().getNome(),
                        "043.783.336-22");
                break;
            default:
                throw new IllegalArgumentException("Tipo de pagamento inválido: " + pedidoDeCompraDTO.tipoDePagamento());
        }
    }
}
