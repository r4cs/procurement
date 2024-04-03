package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface NegociacaoPrecoStrategy {
    BigDecimal negociarPreco(BigDecimal precoAtual, BigDecimal desconto);
}