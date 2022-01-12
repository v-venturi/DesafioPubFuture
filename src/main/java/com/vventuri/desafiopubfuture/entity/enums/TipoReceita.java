package com.vventuri.desafiopubfuture.entity.enums;

    public enum TipoReceita {
    SALARIO("Salário"),
    PRESENTE("Presente"),
    PREMIO("Prêmio"),
    OUTROS("Outros");
    public final String tipoReceita;

    TipoReceita(String tipoReceita) {
    this.tipoReceita = tipoReceita;
    }

    public String getTipoReceita() {
    return tipoReceita;
    }
    }
