package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.DTO.PropostaDeVendaDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.model.entities.PropostaDeVenda;
import br.com.challenge.procurement.core.repositories.PropostaDeVendaRepo;
import br.com.challenge.procurement.core.service.strategy.PagamentoPixPadrao;
import br.com.challenge.procurement.core.service.strategy.PagamentoPixStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropostaDeVendaService {

    private final PropostaDeVendaRepo repo;
    private final PedidoDeCompraService pedidoDeCompraService;
    private final PagamentoPixStrategy pagamentoPixStrategy;
    private final PagamentoPixPadrao pagamentoPix;

    @Autowired
    public PropostaDeVendaService(PropostaDeVendaRepo repo,
                                  PedidoDeCompraService pedidoDeCompraService) {
        this.repo = repo;
        this.pedidoDeCompraService = pedidoDeCompraService;
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
            System.out.println("Nao encontrado");
            return Optional.empty();
        }
    }
    @Transactional
    public String delete(Long id) {
        repo.deleteById(id);
        return "Proposta de venda de id {%s} atualizado.".formatted(id);
    }

    public void processPayment(PropostaDeVendaDTO dto) {
        Optional<PedidoDeCompra> pedidoDeCompra = Optional.of(pedidoDeCompraService.getPedidoDeCompraById(dto.id()).orElseThrow());
//        switch (dto.getTipoPagamento()) {
        switch (pedidoDeCompra.get().getTipoDePagamento()) {
            case PIX:
                pagamentoPixStrategy.processarPagamento(dto.getValorTotal());
                break;
            case CARTAO:
                pagamentoCartaoStrategy.processarPagamento(dto.getValorTotal());
                break;
            case BOLETO:
                pagamentoBoletoStrategy.processarPagamento(dto.getValorTotal());
                break;
            default:
                throw new IllegalArgumentException("Tipo de pagamento inválido: " + dto.getTipoPagamento());
        }

    }
}
