package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int receitaId;
    private double valor;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataRecebimentoEsperado;
    private String descricao;
    @ManyToOne
    @JoinColumn
    private Conta conta;
    @Enumerated(EnumType.STRING)
    private TipoReceita tipoReceita;

}
