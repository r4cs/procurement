package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public class PagamentoAntecipadoPadrao implements PagamentoAntecipadoStrategy {
    @Override
    public BigDecimal calcularValorPagamentoAntecipado(BigDecimal valorTotal) {
        // Por exemplo, pode-se oferecer um desconto fixo para pagamento antecipado
        BigDecimal descontoAntecipado = new BigDecimal("0.05"); // 5% de desconto
        return valorTotal.subtract(valorTotal.multiply(descontoAntecipado));
    }
}
