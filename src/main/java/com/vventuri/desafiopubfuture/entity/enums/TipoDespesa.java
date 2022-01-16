package com.vventuri.desafiopubfuture.entity.enums;

/**
 * The enum Tipo despesa.
 */
public enum TipoDespesa {
    ALIMENTACAO("Alimentação"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    MORADIA("Moradia"),
    ROUPA("Roupa"),
    SAUDE("Saúde"),
    TRANSPORTE("Transporte"),
    OUTROS("Outros");
    public final String tipoDespesa;

    TipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }
}
