package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import com.vventuri.desafiopubfuture.repositories.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {

    @Autowired
    private final DespesaRepository despesaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Despesas> despesas(){
        return despesaRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesas cadastrar(@RequestBody Despesas despesas){
        return despesaRepository.save(despesas);
    }
    @PutMapping(path = "/{codDespesa}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Despesas editar(@PathVariable int codDespesa, @RequestBody Despesas despesas)  {
        Despesas despesa1 = despesaRepository.findById(codDespesa).get();
        despesas.setDespesaId(despesa1.getDespesaId());
        BeanUtils.copyProperties(despesas, despesa1, "codDespesa");
        return despesaRepository.saveAndFlush(despesa1);
    }
    @DeleteMapping(path = "/{codDespesa}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarDespesa(@PathVariable int codDespesa){
        despesaRepository.deleteById(codDespesa);
    }
    @GetMapping(path = "/{tipoDespesa}") // ignoreCase implantado via componente conversor de enum
    public List<Despesas> listarDespesasPorTipo(@PathVariable TipoDespesa tipoDespesa){
        return despesaRepository.findByTipoDespesa(tipoDespesa);
    }

    @GetMapping(path = "/buscarPorPeriodo")
    public ResponseEntity<List<Despesas>> listarPorPeriodo(@RequestParam Date dataInicial,
                                                           @RequestParam Date dataFinal ){
        return new  ResponseEntity<List<Despesas>>(despesaRepository
                .findBetweenDates(dataInicial, dataFinal), HttpStatus.OK);

    }


}
