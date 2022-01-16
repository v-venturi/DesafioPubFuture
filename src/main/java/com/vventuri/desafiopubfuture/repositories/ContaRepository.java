package com.vventuri.desafiopubfuture.repositories;

import com.vventuri.desafiopubfuture.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Conta repository.
 */
@Repository
public interface ContaRepository extends JpaRepository <Conta, Integer> {

    /**
     * Gets saldo poupanca.
     *
     * @return the saldo poupanca
     */
    @Query("SELECT sum(t.saldo) as totalPoupanca FROM conta t WHERE t.tipoConta='POUPANCA'")
    Double getSaldoPoupanca();

    /**
     * Gets saldo conta corrente.
     *
     * @return the saldo conta corrente
     */
    @Query("SELECT sum(t.saldo) as totalContaCorrente FROM conta t WHERE t.tipoConta='CONTA_CORRENTE'")
    Double getSaldoContaCorrente();

    /**
     * Gets saldo carteira.
     *
     * @return the saldo carteira
     */
    @Query("SELECT sum(t.saldo) as totalCarteira FROM conta t WHERE t.tipoConta='CARTEIRA' ")
    Double getSaldoCarteira();

    /**
     * Gets saldo total.
     *
     * @return the saldo total
     */
    @Query ("SELECT sum(t.saldo) as saldo_total FROM conta t ")
    Double getSaldoTotal();







}





