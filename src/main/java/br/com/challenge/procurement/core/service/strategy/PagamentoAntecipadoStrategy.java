package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface PagamentoAntecipadoStrategy {
    BigDecimal calcularValorPagamentoAntecipado(BigDecimal valorTotal);
}
