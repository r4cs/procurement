package br.com.challenge.procurement.core.services.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class PagamentoBoletoPadrao implements PagamentoBoletoStrategy {
    @Override
    public String gerarBoleto(BigDecimal valor, String nomeCliente, String cpfCliente) {
        // Implementação da geração do boleto bancário
        String boleto = "Boleto bancário gerado para o cliente " + nomeCliente + " no valor de: " + valor;
        return boleto;
    }

    @Override
    public String pagarBoleto(BigDecimal valor, String nomeCliente, String cpfCliente) {
        // Implementação da geração do boleto bancário
        String boleto = "Boleto bancário de " + nomeCliente + " pego no valor de: " + valor;
        return boleto;
    }


}

