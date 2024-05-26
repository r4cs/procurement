package br.com.challenge.procurement.core.services.strategy;

import java.math.BigDecimal;

public interface DescontoVolumeStrategy {
    BigDecimal calcularDescontoVolume(int quantidade);
}
