package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public class NegociacaoPrecoPadrao implements NegociacaoPrecoStrategy {
    @Override
    public BigDecimal negociarPreco(BigDecimal precoAtual, BigDecimal desconto) {
        BigDecimal precoNegociado = precoAtual.subtract(precoAtual.multiply(desconto));
        return precoNegociado.compareTo(BigDecimal.ZERO) >= 0 ? precoNegociado : BigDecimal.ZERO;
    }
}
