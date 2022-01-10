package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receitaId;
    private double valor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecebimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRecebimentoEsperado;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoReceita tipoReceita;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cod_conta_receita")
    private Conta conta;


}
