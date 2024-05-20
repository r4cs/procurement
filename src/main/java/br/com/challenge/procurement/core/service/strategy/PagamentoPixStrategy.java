package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface PagamentoPixStrategy {
    void realizarPagamentoPIX(BigDecimal valor);
    void processarPagamentoPix(BigDecimal valor, String chavePix);

    void receberEmPix(BigDecimal valor, String chavePix);
}
