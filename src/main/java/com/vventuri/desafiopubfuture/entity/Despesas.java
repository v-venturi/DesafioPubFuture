package com.vventuri.desafiopubfuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "despesas")
@Builder
public class Despesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int despesaId;
    private double valor;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataPagamento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataPagamentoEsperado;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cod_conta_despesa")
    private Conta conta;








}
