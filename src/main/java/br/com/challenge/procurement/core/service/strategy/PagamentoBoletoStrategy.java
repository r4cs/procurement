package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface PagamentoBoletoStrategy {
    String gerarBoleto(BigDecimal valor, String nomeCliente, String cpfCliente);
}
