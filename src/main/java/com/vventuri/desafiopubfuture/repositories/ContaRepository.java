package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Integer> {

}
