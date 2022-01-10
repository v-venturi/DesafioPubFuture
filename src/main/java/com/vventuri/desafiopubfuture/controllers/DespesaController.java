package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.repositories.DespesaRepository;
import com.vventuri.desafiopubfuture.services.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {
    @Autowired
    private final DespesaService despesaService;
    @Autowired
    private final DespesaRepository despesaRepository;
    private final ContaRepository contaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Despesas> despesas(){
        return despesaRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesas cadastrar(@RequestBody Despesas despesas){
//        conta.setCodConta(despesas.getConta().getCodConta());
//        contaRepository.save(conta);
        return despesaRepository.save(despesas);
    }

}
