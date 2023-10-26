package br.com.challenge.procurement.apiController;

import br.com.challenge.procurement.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.model.PedidoDeCompra;
import br.com.challenge.procurement.service.PedidoDeCompraService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;
@RestController
@RequestMapping(value="/api/pedido_de_compra")
public class PedidoDeCompraController {

    private final PedidoDeCompraService pedidoDeCompraService;

    public PedidoDeCompraController(PedidoDeCompraService pedidoDeCompraService) {
        this.pedidoDeCompraService = pedidoDeCompraService;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PedidoDeCompraDTO dto) {
        System.out.println("Dados pedido de compra: " + dto);
        pedidoDeCompraService.criarPedidoDeCompra(dto);
    }

    @GetMapping
    public List<PedidoDeCompra> listarTodos() {
        return pedidoDeCompraService.listarPedidosDeCompra();
    }

    @GetMapping(value = "/{id}")
    public Optional<PedidoDeCompra> obterPedidoDeCompra(@PathVariable Long id){
        return pedidoDeCompraService.getPedidoDeCompraById(id);
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarPedidoDeCompra(@PathVariable Long id, @RequestBody @Valid PedidoDeCompra novaPedido) {
        pedidoDeCompraService.updatePedidoDeCompra(id, novaPedido);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePedidoDeCompra(@PathVariable Long id) {
        pedidoDeCompraService.deletePedidoDeCompra(id);
    }


}
