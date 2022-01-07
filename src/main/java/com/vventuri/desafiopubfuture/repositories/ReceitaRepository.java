package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receitas, Integer> {
}
