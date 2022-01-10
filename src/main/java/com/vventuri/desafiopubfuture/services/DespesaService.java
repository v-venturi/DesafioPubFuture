package com.vventuri.desafiopubfuture.services;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;
    @Autowired
    private final ContaRepository contaRepository;
    private ContaService contaService;



//    public Despesas cadastrarDespesa(Despesas despesa ){
////        despesa.getConta().setSaldo(despesa.getConta().getSaldo() - despesa.getValor());
//
//        contaRepository.save(despesa.getConta());
//        return despesaRepository.save(despesa);
//    }
}
