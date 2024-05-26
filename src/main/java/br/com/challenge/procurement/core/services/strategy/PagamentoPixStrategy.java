package br.com.challenge.procurement.core.services.strategy;

import java.math.BigDecimal;

public interface PagamentoPixStrategy {
    void realizarPagamentoPIX(BigDecimal valor);
    void processarPagamentoPix(BigDecimal valor, String chavePix);
}
