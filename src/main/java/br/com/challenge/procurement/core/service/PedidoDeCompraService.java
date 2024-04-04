package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
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
    private final PedidoDeCompraRepo pedidoDeCompraRepo;
    private final PagamentoPixPadrao pagamentoPix;
    private final PagamentoBoletoPadrao pagamentoBoleto;
    private final PagamentoCartaoPadrao pagamentoCartao;
    @Autowired
    public PedidoDeCompraService(PedidoDeCompraRepo pedidoDeCompraRepo,
                                 PagamentoPixPadrao pagamentoPix,
                                 PagamentoBoletoPadrao pagamentoBoleto,
                                 PagamentoCartaoPadrao pagamentoCartao) {
        this.pedidoDeCompraRepo = pedidoDeCompraRepo;
        this.pagamentoPix = pagamentoPix;
        this.pagamentoBoleto = pagamentoBoleto;
        this.pagamentoCartao = pagamentoCartao;
    }



    @Transactional
    public PedidoDeCompra criarPedidoDeCompra(PedidoDeCompraDTO dto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(dto);

        return pedidoDeCompraRepo.save(pedidoDeCompra);
    }

    public Page<PedidoDeCompra> listarPedidosDeCompra(Pageable pageable) {
        return pedidoDeCompraRepo.findAll(pageable);
    }

    public Optional<PedidoDeCompra> getPedidoDeCompraById(Long id) {
        return pedidoDeCompraRepo.findById(id);
    }

    @Transactional
    public PedidoDeCompra updatePedidoDeCompra(Long id, PedidoDeCompra updatedPedidoDeCompra) {
        Optional<PedidoDeCompra> pedDeCompAntigo = pedidoDeCompraRepo.findById(id);
        System.out.println("json do pedido de compra atualizado: "+ updatedPedidoDeCompra);

        if (pedDeCompAntigo.isPresent()) {
            PedidoDeCompra pedidoDeCompra = pedDeCompAntigo.get();
            pedidoDeCompra.setData_pedido(LocalDateTime.now());
            pedidoDeCompra.setSolicitacao(pedidoDeCompra.getSolicitacao());
            return pedidoDeCompraRepo.save(pedidoDeCompra);
        } else {
            // throw  new EnderecoNotFoundException(id);
            System.out.println("criar classe PedidoDeCompraNotFoundException" );
            return null;
        }
    }

    @Transactional
    public String deletePedidoDeCompra(Long id) {
        pedidoDeCompraRepo.deleteById(id);
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
