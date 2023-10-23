package br.com.challenge.procurement.service;

import br.com.challenge.procurement.model.DTO.EnderecoDTO;
import br.com.challenge.procurement.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.model.Endereco;
import br.com.challenge.procurement.model.PedidoDeCompra;
import br.com.challenge.procurement.repositories.EnderecoRepo;
import br.com.challenge.procurement.repositories.PedidoDeCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoDeCompraService {
    private PedidoDeCompraRepo pedidoDeCompraRepo;

    public PedidoDeCompraService(@Autowired PedidoDeCompraRepo pedidoDeCompraRepo) {
        this.pedidoDeCompraRepo = pedidoDeCompraRepo;
    }

    public void criarPedidoDeCompra(PedidoDeCompraDTO dto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(dto);
        pedidoDeCompraRepo.save(pedidoDeCompra);
    }

    public List<PedidoDeCompra> listarPedidosDeCompra() {
        return pedidoDeCompraRepo.findAll();
    }

    public Optional<PedidoDeCompra> getPedidoDeCompraById(Long id) {
        return pedidoDeCompraRepo.findById(id);
    }

    public PedidoDeCompra updatePedidoDeCompra(Long id, PedidoDeCompra updatedPedidoDeCompra) {
        Optional<PedidoDeCompra> pedDeCompAntigo = pedidoDeCompraRepo.findById(id);

        if (pedDeCompAntigo.isPresent()) {
            PedidoDeCompra pedidoDeCompra = pedDeCompAntigo.get();
            pedidoDeCompra.setSolicitacaoCompra(updatedPedidoDeCompra.getSolicitacaoCompra());
            pedidoDeCompra.setFornecedor(updatedPedidoDeCompra.getFornecedor());
            pedidoDeCompra.setData_entrega_prevista(updatedPedidoDeCompra.getData_entrega_prevista());
            pedidoDeCompra.setData_pedido(updatedPedidoDeCompra.getData_pedido());
            return pedidoDeCompraRepo.save(pedidoDeCompra);
        } else {
            // throw  new EnderecoNotFoundException(id);
            System.out.println("criar classe PedidoDeCompraNotFoundException" );
            return null;
        }
    }

    public void deletePedidoDeCompra(Long id) {
        pedidoDeCompraRepo.deleteById(id);
    }

}
