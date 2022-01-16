package com.vventuri.desafiopubfuture.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * The type Receitas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "receitas")
@Builder

public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receitaId;
    private double valor;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataRecebimento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataRecebimentoEsperado;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoReceita tipoReceita;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cod_conta_receita")
    private Conta conta;
}
