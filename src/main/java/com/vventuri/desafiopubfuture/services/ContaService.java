package com.vventuri.desafiopubfuture.services;

import com.vventuri.desafiopubfuture.entity.Conta;
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

    public List<Conta> listarTodas(){
        return contaRepository.findAll();
    }
    public Conta procurarConta(int conta){
        return contaRepository.findById(conta).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    public Conta cadatrarConta(Conta conta){
        return contaRepository.save(conta);
    }
    public void editarConta(Conta conta){
        Conta contaEditada = procurarConta(conta.getConta());
        conta.setConta(contaEditada.getConta());
        contaRepository.save(conta);
    }
    public void removerConta(int conta){
        contaRepository.delete(procurarConta(conta));
    }
}
