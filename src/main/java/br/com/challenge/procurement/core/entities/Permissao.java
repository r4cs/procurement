package br.com.challenge.procurement.core.entities;

public enum Permissao {
    SOLICITANTE("Solicitante"),
    APROVADOR("Aprovador");

    private final String tipoDePermissao;

    Permissao(String tipoDePermissao){
        this.tipoDePermissao = tipoDePermissao;
    };

    public String getTipoDePermissao() {
        return tipoDePermissao;
    }

    @Override
    public String toString() {
        return tipoDePermissao;
    }
}
