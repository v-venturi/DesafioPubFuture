package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.services.ContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaController {
    @Autowired
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> contas() {
        return ResponseEntity.ok(contaService.listarTodas());

    }

    @GetMapping(path = "/{conta}")
    public ResponseEntity<Conta> listarPorConta(@PathVariable Integer conta) {
        return ResponseEntity.ok(contaService.procurarConta(conta));
    }

    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        return new ResponseEntity<>(contaService.cadatrarConta(conta), HttpStatus.CREATED);
    }

    @PutMapping(path = "editar/{conta}")
    public ResponseEntity<Void> editar(@RequestBody Conta conta) {
        contaService.editarConta(conta);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "deletar/{conta}")
    public ResponseEntity<Void> deletar(@PathVariable int conta) {
        contaService.removerConta(conta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "transferir/{contaOrigem}/{contaDestino}/{valorTransferido}")
    public ResponseEntity<Void> transferirSaldo(@PathVariable(value = "contaOrigem") Conta contaOrigem,
                                                @PathVariable(value = "contaDestino") Conta contaDestino,
                                                @PathVariable(value = "valorTransferido") Double valorTransferido) {
        contaService.sacar(contaOrigem.getConta(), valorTransferido);
        contaService.depositar(contaDestino.getConta(), valorTransferido);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
