package br.com.challenge.procurement.core.entities;

public enum Permissao {
    SOLICITANTE("SOLICITANTE"),
    APROVADOR("APROVADOR");

    private final String tipoDePermissao;

    Permissao(String tipoDePermissao){
        this.tipoDePermissao = tipoDePermissao.toUpperCase();
    };

    public String getTipoDePermissao() {
        return tipoDePermissao;
    }

    @Override
    public String toString() {
        return tipoDePermissao;
    }
}
