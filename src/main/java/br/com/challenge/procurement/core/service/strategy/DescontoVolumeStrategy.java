package br.com.challenge.procurement.core.service.strategy;

import java.math.BigDecimal;

public interface DescontoVolumeStrategy {
    BigDecimal calcularDescontoVolume(int quantidade);
}
