package br.com.challenge.procurement.core.services;

import br.com.challenge.procurement.core.models.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.models.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.models.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.repositories.PedidoDeCompraRepo;
import br.com.challenge.procurement.core.services.strategy.PagamentoBoletoPadrao;
import br.com.challenge.procurement.core.services.strategy.PagamentoCartaoPadrao;
import br.com.challenge.procurement.core.services.strategy.PagamentoPixPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
    public String criarPedidoDeCompra(PedidoDeCompraDTO dto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(dto);

        repo.save(pedidoDeCompra);
        return "Pedido de compra criado com sucesso.";
    }

    public Page<PedidoDeCompra> listarPedidosDeCompra(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<PedidoDeCompra> listAll() {return repo.findAll();}

    public Optional<PedidoDeCompra> getPedidoDeCompraById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public String updatePedidoDeCompra(Long id, PedidoDeCompra updatedPedidoDeCompra) {
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
            // throw  new EnderecoNotFoundException(id);
            System.out.println("criar classe PedidoDeCompraNotFoundException" );
            return "Algo deu errado, verifique os dados inseridos.";
        }
    }

    @Transactional
    public String deletePedidoDeCompra(Long id) {
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