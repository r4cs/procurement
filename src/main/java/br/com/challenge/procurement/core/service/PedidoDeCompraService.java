package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.entities.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.repositories.PedidoDeCompraRepo;
import br.com.challenge.procurement.core.service.strategy.AprovarPedidoStrategy;
import br.com.challenge.procurement.core.service.strategy.RejeitarPedidoStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoDeCompraService {
    private final PedidoDeCompraRepo pedidoDeCompraRepo;
    private final AprovarPedidoStrategy aprovarPedidoStrategy;
    private final RejeitarPedidoStrategy rejeitarPedidoStrategy;

    @Autowired
    public PedidoDeCompraService(PedidoDeCompraRepo pedidoDeCompraRepo,
                                 AprovarPedidoStrategy aprovarPedidoStrategy,
                                 RejeitarPedidoStrategy rejeitarPedidoStrategy)
            {
                this.pedidoDeCompraRepo = pedidoDeCompraRepo;
                this.aprovarPedidoStrategy = aprovarPedidoStrategy;
                this.rejeitarPedidoStrategy = rejeitarPedidoStrategy;
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

    public String deletePedidoDeCompra(Long id) {
        pedidoDeCompraRepo.deleteById(id);
        return "Pedido de compra excluído";
    }

}
