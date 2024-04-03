package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public class PagamentoBoletoPadrao implements PagamentoBoletoStrategy {
    @Override
    public String gerarBoleto(BigDecimal valor, String nomeCliente, String cpfCliente) {
        // Implementação da geração do boleto bancário
        String boleto = "Boleto bancário gerado para o cliente " + nomeCliente + " no valor de: " + valor;
        return boleto;
    }
}

