package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface PagamentoCartaoStrategy {
    void realizarPagamentoCartao(BigDecimal valor,
                                 String tipoCartao,
                                 String numeroCartao,
                                 String nomeTitular,
                                 String dataValidade,
                                 String cvv);
    void processarPagamentoCartao(BigDecimal valor,
                                         String tipoCartao,
                                         String numeroCartao,
                                         String nomeTitular,
                                         String dataValidade,
                                         String cvv);
}
