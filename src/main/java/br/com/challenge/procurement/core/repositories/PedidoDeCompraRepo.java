package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.model.entities.PedidoDeCompra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDeCompraRepo extends JpaRepository<PedidoDeCompra, Long> {
    @Override
    Page<PedidoDeCompra> findAll(Pageable pageable);
}
