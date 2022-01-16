package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Receitas;
import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * The interface Receita repository.
 */
public interface ReceitaRepository extends JpaRepository<Receitas, Integer> {

    /**
     * Find by tipo receita list.
     *
     * @param tipoReceita the tipo receita
     * @return the list
     */
    List<Receitas> findByTipoReceita(TipoReceita tipoReceita);

    /**
     * Find between dates list.
     *
     * @param dataInicial the data inicial
     * @param dataFinal   the data final
     * @return the list
     */
    @Query("SELECT t FROM receitas t WHERE t.dataRecebimento BETWEEN :dataInicial and :dataFinal")
    List<Receitas> findBetweenDates(Date dataInicial, Date dataFinal);

}
