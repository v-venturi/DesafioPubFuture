package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesas, Integer> {

    List<Despesas> findByTipoDespesa(TipoDespesa tipoDespesa);

    @Query("SELECT t FROM despesas t WHERE t.dataPagamento BETWEEN :dataInicial and :dataFinal")
    List<Despesas> findBetweenDates(Date dataInicial, Date dataFinal);

}
