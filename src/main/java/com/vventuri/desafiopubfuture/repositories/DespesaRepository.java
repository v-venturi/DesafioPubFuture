package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesas, Integer> {
}
