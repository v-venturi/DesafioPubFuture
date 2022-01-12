package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Receitas;
import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receitas, Integer> {

    List<Receitas> findByTipoReceita(TipoReceita tipoReceita);

    @Query("SELECT t FROM receitas t WHERE t.dataRecebimento BETWEEN :dataInicial and :dataFinal")
    List<Receitas> findBetweenDates(Date dataInicial, Date dataFinal);

}
