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
    public ResponseEntity<List<Conta>> contas(){
        return ResponseEntity.ok(contaService.listarTodas());

    }
    @GetMapping(path = "/{conta}")
    public ResponseEntity<Conta> listarPorConta(@PathVariable Integer conta){
        return ResponseEntity.ok(contaService.procurarConta(conta));
    }
    @PostMapping
    public ResponseEntity<Conta> cadastrar (@RequestBody Conta conta){
        return new ResponseEntity<>(contaService.cadatrarConta(conta), HttpStatus.CREATED);
    }
    @PutMapping(path = "/{conta}")
    public ResponseEntity<Void> editar(@RequestBody Conta conta){
        contaService.editarConta(conta);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping(path = "/{conta}")
    public ResponseEntity<Void> deletar (@PathVariable int conta){
        contaService.removerConta(conta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
