package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.model.entities.TipoDePagamento;
import br.com.challenge.procurement.core.repositories.PropostaDeVendaRepo;
import br.com.challenge.procurement.core.service.strategy.PagamentoBoletoPadrao;
import br.com.challenge.procurement.core.service.strategy.PagamentoCartaoPadrao;
import br.com.challenge.procurement.core.service.strategy.PagamentoPixPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PropostaDeVendaService {

    private final PropostaDeVendaRepo repo;
    private final PagamentoPixPadrao pagamentoPix;
    private final PagamentoBoletoPadrao pagamentoBoleto;
    private final PagamentoCartaoPadrao pagamentoCartao;

    @Autowired
    public PropostaDeVendaService(PropostaDeVendaRepo repo,
                                  PagamentoPixPadrao pagamentoPix,
                                  PagamentoBoletoPadrao pagamentoBoleto,
                                  PagamentoCartaoPadrao pagamentoCartao) {
        this.repo = repo;
        this.pagamentoPix = pagamentoPix;
        this.pagamentoBoleto = pagamentoBoleto;
        this.pagamentoCartao = pagamentoCartao;
    };

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
            propostaDeVendaAtualizada.setPedido_compra(novoPropostaDeVenda.getPedido_compra());
            propostaDeVendaAtualizada.setValor_unitario(novoPropostaDeVenda.getValor_unitario());
            propostaDeVendaAtualizada.setValor_total(novoPropostaDeVenda.getValor_total());
            return Optional.of(repo.save(propostaDeVendaAtualizada));
        } else {
            System.out.println("Proposta não encontrada");
            return Optional.empty();
        }
    }

    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Proposta de venda de id {%s} atualizado.".formatted(id);
    }

    public void processPayment(PropostaDeVendaDTO dto, TipoDePagamento tipoDePagamento) {
        // !!!!!!
        // deverá haver uma tela de preenchimento desses dados, por hora teremos um dummie input
        // !!!!!!
//        switch (pedidoDeCompra.get().getTipoDePagamento()) {
        switch (tipoDePagamento) {
            case PIX: // valor total e chave pix (email) como parametros
                pagamentoPix.processarPagamentoPix(dto.valor_total(), dto.pedido_compra().getSolicitante().getEmail());
                break;

            case CARTAO:
                pagamentoCartao.processarPagamentoCartao(dto.valor_total(),
                                                "credito",
                                             "0000 0000 0000 0000",
                                                          dto.pedido_compra().getSolicitante().getNome(),
                                              "07/2033",
                                                     "499");
                break;
            case BOLETO:
                pagamentoBoleto.gerarBoleto(dto.valor_total(),
                                            dto.pedido_compra().getSolicitante().getNome(),
                                  "043.783.336-22");
                break;

            default:
                throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipoDePagamento);
        }
    }
}
