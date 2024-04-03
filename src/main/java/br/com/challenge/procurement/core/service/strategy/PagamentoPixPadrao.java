package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public class PagamentoPixPadrao implements PagamentoPixStrategy {
    @Override
    public void realizarPagamentoPIX(BigDecimal valor) {
        // Implementação do pagamento via PIX
        System.out.println("Pagamento via PIX no valor de: " + valor);
    }
}
