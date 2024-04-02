package br.com.challenge.procurement.core.repositories;

import br.com.challenge.procurement.core.entities.PropostaDeVenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaDeVendaRepo extends JpaRepository<PropostaDeVenda, Long> {

    Page<PropostaDeVenda> findAll(Pageable pageable);

}
