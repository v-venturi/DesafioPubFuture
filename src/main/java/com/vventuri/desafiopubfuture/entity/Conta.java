package com.vventuri.desafiopubfuture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vventuri.desafiopubfuture.entity.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_conta")
    private int codConta;
    private double saldo;
    private String banco;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    @OneToMany
    @JsonIgnore
    private List<Conta> contas;

}
