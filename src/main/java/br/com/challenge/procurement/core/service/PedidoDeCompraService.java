package br.com.challenge.procurement.core.service;

import br.com.challenge.procurement.core.model.entities.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.repositories.PedidoDeCompraRepo;
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

    public PedidoDeCompraService(@Autowired PedidoDeCompraRepo pedidoDeCompraRepo) {
        this.pedidoDeCompraRepo = pedidoDeCompraRepo;
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
