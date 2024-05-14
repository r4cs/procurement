package br.com.challenge.procurement.core.apiController;

import br.com.challenge.procurement.core.model.DTO.PedidoDeCompraDTO;
import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import br.com.challenge.procurement.core.model.entities.Produto;
import br.com.challenge.procurement.core.service.PedidoDeCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/pedido")
public class PedidoDeCompraController {

    private final PedidoDeCompraService service;

    public PedidoDeCompraController(PedidoDeCompraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid PedidoDeCompraDTO dto) {
        System.out.println("Dados pedido de compra: " + dto);
        return ResponseEntity.ok(service.criarPedidoDeCompra(dto));
    }


    @GetMapping
    public ResponseEntity<Page<PedidoDeCompra>> listarTodos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, solicitacao_id, tipoDePagamento, data_pedido")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {
        Pageable defaultPageable = PageRequest.of(
                page,
                size,
                Sort.by(orderBy)
        );
        Page<PedidoDeCompra> pedidosDeCompra = service.listarPedidosDeCompra(defaultPageable);
        return ResponseEntity.ok(pedidosDeCompra);
    }

    @Operation(hidden = true)
    @GetMapping("/all")
    public ResponseEntity<List<PedidoDeCompra>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<PedidoDeCompra>> obterPedidoDeCompra(@PathVariable Long id){
        return ResponseEntity.ok(service.getPedidoDeCompraById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> atualizarPedidoDeCompra(@PathVariable Long id, @RequestBody @Valid PedidoDeCompra novaPedido) {
        return ResponseEntity.ok(service.updatePedidoDeCompra(id, novaPedido));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePedidoDeCompra(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletePedidoDeCompra(id));
    }
}
