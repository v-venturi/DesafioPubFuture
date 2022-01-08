package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Despesas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int despesaId;
    private double valor;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataPagamentoEsperado;
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;
    @ManyToOne
    @JoinColumn
    private Conta conta;
}
