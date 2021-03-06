package com.vventuri.desafiopubfuture.entity.enums;

/**
 * The enum Tipo conta.
 */
public enum TipoConta {
    CARTEIRA("Carteira"),
    CONTA_CORRENTE("Conta Corrente"),
    POUPANCA("Poupança");
    public final String tipoConta;

    TipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }
}
