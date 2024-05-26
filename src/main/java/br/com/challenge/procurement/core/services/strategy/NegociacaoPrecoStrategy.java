package br.com.challenge.procurement.core.services.strategy;

import java.math.BigDecimal;

public interface NegociacaoPrecoStrategy {
    BigDecimal negociarPreco(BigDecimal precoAtual, BigDecimal desconto);
}