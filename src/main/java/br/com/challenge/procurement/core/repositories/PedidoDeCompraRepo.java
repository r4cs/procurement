package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.entities.PedidoDeCompra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDeCompraRepo extends JpaRepository<PedidoDeCompra, Long> {
    Page<PedidoDeCompra> findAll(Pageable pageable);
}
