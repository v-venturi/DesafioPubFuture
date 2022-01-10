package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Despesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int despesaId;
    private double valor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamentoEsperado;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cod_conta_despesa")
    private Conta conta;


}
