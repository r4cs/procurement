package br.com.challenge.procurement.core.model.entities;

public enum TipoDePagamento {
    PIX("Pix"),
    BOLETO("Boleto"),
    CARTAO("Cartão");

    private final String tipoDePagamento;

    TipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento=tipoDePagamento;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }
}
