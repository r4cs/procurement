package br.com.challenge.procurement.core.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class NegociacaoPrecoPadrao implements NegociacaoPrecoStrategy {
    @Override
    public BigDecimal negociarPreco(BigDecimal precoAtual, BigDecimal desconto) {
        BigDecimal precoNegociado = precoAtual.subtract(precoAtual.multiply(desconto));
        return precoNegociado.compareTo(BigDecimal.ZERO) >= 0 ? precoNegociado : BigDecimal.ZERO;
    }
}