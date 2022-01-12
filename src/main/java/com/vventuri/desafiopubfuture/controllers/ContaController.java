package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private final ContaRepository contaRepository;

    @GetMapping
    public ResponseEntity<List<Conta>> contas() {
        return ResponseEntity.ok(contaService.listarTodas());

    }

    @GetMapping(path = "/{codConta}")
    public ResponseEntity<Conta> listarPorConta(@PathVariable Integer codConta) {
        return ResponseEntity.ok(contaService.procurarConta(codConta));
    }

    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        return new ResponseEntity<>(contaService.cadatrarConta(conta), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{codConta}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Conta editar(@PathVariable int codConta, @RequestBody Conta conta) {
        Conta conta1 = contaRepository.findById(codConta).get();
        BeanUtils.copyProperties(conta, conta1, "codConta");
        return contaRepository.save(conta1);

    }

    @DeleteMapping(path = "/{codConta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable int codConta) {
        contaRepository.deleteById(codConta);

    }

    @PutMapping(path = "transferir/{contaOrigem}/{contaDestino}/{valorTransferido}")
    public ResponseEntity<Void> transferirSaldo(@PathVariable(value = "contaOrigem") Conta contaOrigem,
                                                @PathVariable(value = "contaDestino") Conta contaDestino,
                                                @PathVariable(value = "valorTransferido") Double valorTransferido)
            throws FundsNotAvaliableException {
        contaService.sacar(contaOrigem.getCodConta(), valorTransferido);
        contaService.depositar(contaDestino.getCodConta(), valorTransferido);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
