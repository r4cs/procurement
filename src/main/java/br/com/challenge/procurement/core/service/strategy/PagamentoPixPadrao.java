package br.com.challenge.procurement.core.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoPixPadrao implements PagamentoPixStrategy {
    @Override
    public void realizarPagamentoPIX(BigDecimal valor) {
        // Implementação do pagamento via PIX
        System.out.println("Pagamento via PIX no valor de: " + valor);
    }

    @Override
    public void processarPagamentoPix(BigDecimal valor, String chavePix) {
        System.out.println("Gerando PIX no valor de: " + valor + " na chave pix " + chavePix);
    }


}
