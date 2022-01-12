package com.vventuri.desafiopubfuture.services;

import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;
    @Autowired
    private final ContaRepository contaRepository;
    private ContaService contaService;
}

