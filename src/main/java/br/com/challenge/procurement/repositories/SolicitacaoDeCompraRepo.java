package br.com.challenge.procurement.repositories;


import br.com.challenge.procurement.model.SolicitacaoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoDeCompraRepo extends JpaRepository<SolicitacaoDeCompra, Long> {

}
