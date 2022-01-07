package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;

import java.time.LocalDateTime;

public class Teste {
    public static void main(String[] args) {
        Receitas receitas = new Receitas();
        receitas.setTipoReceita(TipoReceita.PRESENTE);
        receitas.setConta(33333333);
        receitas.setDataRecebimento(LocalDateTime.now());
        System.out.print(receitas);

    }
}
