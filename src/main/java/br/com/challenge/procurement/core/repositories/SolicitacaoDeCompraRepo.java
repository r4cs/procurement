package br.com.challenge.procurement.core.repositories;


import br.com.challenge.procurement.core.entities.SolicitacaoDeCompra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoDeCompraRepo extends JpaRepository<SolicitacaoDeCompra, Long> {

    Page<SolicitacaoDeCompra> findAll(Pageable pageable);

}
