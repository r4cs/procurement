package br.com.challenge.procurement.core.models.entities;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("Pending", "O pedido está aguardando processamento."),
    PROCESSING("Processing", "O pedido está sendo processado."),
    ANALYSIS("Analysis", "O pedido está em análise de aprovação."),
    SHIPPED("Shipped", "O pedido foi enviado para entrega."),
    DELIVERED("Delivered", "O pedido foi entregue com sucesso."),
    CANCELED("Canceled", "O pedido foi cancelado.");

    private final String status_code;
    private final String description;

    Status(String status_code, String description) {
        this.status_code = status_code;
        this.description = description;
    }

    @Override
    public String toString() {
        return status_code;
    }
}
