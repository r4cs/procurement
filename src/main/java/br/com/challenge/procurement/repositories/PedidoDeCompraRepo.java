package br.com.challenge.procurement.repositories;

import br.com.challenge.procurement.model.PedidoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDeCompraRepo extends JpaRepository<PedidoDeCompra, Long> {

}
