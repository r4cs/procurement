package br.com.challenge.procurement.core.services.strategy;

import java.math.BigDecimal;

public class DescontoVolumePadrao implements DescontoVolumeStrategy {
    @Override
    public BigDecimal calcularDescontoVolume(int quantidade) {
        BigDecimal desconto = BigDecimal.ZERO;
        if (quantidade >= 10 && quantidade < 20) {
            desconto = new BigDecimal("0.05"); // 5% de desconto para 10 ou mais produtos
        } else if (quantidade >= 20) {
            desconto = new BigDecimal("0.1"); // 10% de desconto para 20 ou mais produtos
        }
        return desconto;
    }
}

