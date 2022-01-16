package com.vventuri.desafiopubfuture.services;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Conta service.
 */
@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    /**
     * Listar todas contas.
     *
     * @return the list
     */
    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    /**
     * Procurar conta.
     *
     * @param conta the conta
     * @return the conta
     */
    public Conta procurarConta(int conta) {
        return contaRepository.findById(conta).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    /**
     * Cadastrar conta.
     *
     * @param conta the conta
     * @return the conta
     */
    public Conta cadastrarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    /**
     * Sacar.
     *
     * @param conta            the conta
     * @param valorTransferido the valor transferido
     * @throws FundsNotAvaliableException the funds not avaliable exception
     */
    public void sacar(int conta, double valorTransferido) throws FundsNotAvaliableException {
        Conta conta1 = procurarConta(conta);
        if (conta1.getSaldo() < valorTransferido) {
            throw new FundsNotAvaliableException("Saldo em conta menor que o valor solicitado");

        }else
        conta1.setSaldo(conta1.getSaldo() - valorTransferido);
        contaRepository.save(conta1);
    }

    /**
     * Depositar.
     *
     * @param conta            the conta
     * @param valorTransferido the valor transferido
     */
    public void depositar(int conta, double valorTransferido) {
        Conta conta2 = procurarConta(conta);
        conta2.setSaldo(conta2.getSaldo() + valorTransferido);
        contaRepository.save(conta2);
    }

}
