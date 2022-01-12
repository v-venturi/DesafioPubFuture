package com.vventuri.desafiopubfuture.services;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Conta procurarConta(int conta) {
        return contaRepository.findById(conta).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    public Conta cadatrarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public void sacar(int conta, double valorTransferido) throws FundsNotAvaliableException {
        Conta conta1 = procurarConta(conta);
        if (conta1.getSaldo() < valorTransferido) {
            throw new FundsNotAvaliableException("Saldo em conta menor que o valor solicitado," +
                    " valor disponível no momento: R$ " + conta1.getSaldo());
        }
        conta1.setSaldo(conta1.getSaldo() - valorTransferido);
        contaRepository.save(conta1);
    }

    public void depositar(int conta, double valorTransferido) {
        Conta conta2 = procurarConta(conta);
        conta2.setSaldo(conta2.getSaldo() + valorTransferido);
        contaRepository.save(conta2);
    }
}
