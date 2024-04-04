package br.com.challenge.procurement.core.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PagamentoCartaoPadrao implements PagamentoCartaoStrategy {
    @Override
    public void realizarPagamentoCartao(BigDecimal valor,
                                        String tipoCartao,
                                        String numeroCartao,
                                        String nomeTitular,
                                        String dataValidade,
                                        String cvv) {
        // Implementação do pagamento com cartão
        System.out.println("Pagamento com cartão " + tipoCartao + " no valor de: " + valor);
    }

    @Override
    public void processarPagamentoCartao(BigDecimal valor,
                                        String tipoCartao,
                                        String numeroCartao,
                                        String nomeTitular,
                                        String dataValidade,
                                        String cvv) {
        // Implementação de processar pagamento com cartão
        System.out.println("Processando pagamento com cartão " + tipoCartao + " no valor de: " + valor);
    }


}
