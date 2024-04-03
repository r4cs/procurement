package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public class PagamentoCartaoPadrao implements PagamentoCartaoStrategy {
    @Override
    public void realizarPagamentoCartao(BigDecimal valor, String tipoCartao, String numeroCartao, String nomeTitular, String dataValidade, String cvv) {
        // Implementação do pagamento com cartão
        System.out.println("Pagamento com cartão " + tipoCartao + " no valor de: " + valor);
    }
}
