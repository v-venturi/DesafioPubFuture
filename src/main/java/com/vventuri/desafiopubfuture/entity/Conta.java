package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int conta;
    private double saldo;
    private String instituicaoFinanceira;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

}
