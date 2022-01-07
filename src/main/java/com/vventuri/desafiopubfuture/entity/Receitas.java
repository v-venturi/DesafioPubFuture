package com.vventuri.desafiopubfuture.entity;

import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
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
public class Receitas {
    private double valor;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataRecebimentoEsperado;
    private String descricao;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int conta;
    @Enumerated(EnumType.STRING)
    private TipoReceita tipoReceita;

}
