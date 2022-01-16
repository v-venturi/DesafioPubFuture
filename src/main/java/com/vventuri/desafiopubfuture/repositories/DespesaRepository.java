package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * The interface Despesa repository.
 */
public interface DespesaRepository extends JpaRepository<Despesas, Integer> {

    /**
     * Find by tipo despesa list.
     *
     * @param tipoDespesa the tipo despesa
     * @return the list
     */
    List<Despesas> findByTipoDespesa(TipoDespesa tipoDespesa);

    /**
     * Find between dates list.
     *
     * @param dataInicial the data inicial
     * @param dataFinal   the data final
     * @return the list
     */
    @Query("SELECT t FROM despesas t WHERE t.dataPagamento BETWEEN :dataInicial and :dataFinal")
    List<Despesas> findBetweenDates(Date dataInicial, Date dataFinal);

   }
