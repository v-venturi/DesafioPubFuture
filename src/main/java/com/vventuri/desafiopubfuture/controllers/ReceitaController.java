package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Receitas;
import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import com.vventuri.desafiopubfuture.repositories.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("receitas")
@RequiredArgsConstructor
public class ReceitaController {

    @Autowired
    private final ReceitaRepository receitaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Receitas> receitas() {
        return receitaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receitas cadastrar(@RequestBody Receitas receitas) {
        return receitaRepository.save(receitas);
    }

    @PutMapping(path = "/{codReceita}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Receitas editar(@PathVariable int codReceita, @RequestBody Receitas receitas) {
        Receitas receita1 = receitaRepository.findById(codReceita).get();
        receitas.setReceitaId(receita1.getReceitaId());
        BeanUtils.copyProperties(receitas, receita1, "codReceita");
        return receitaRepository.saveAndFlush(receita1);
    }

    @DeleteMapping(path = "/{codReceita}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarReceita(@PathVariable int codReceita) {
        receitaRepository.deleteById(codReceita);
    }

    @GetMapping(path = "/{tipoReceita}") // ignoreCase implantado via componente conversor de enum
    @ResponseStatus(HttpStatus.OK)
    public List<Receitas> listarReceitasPorTipo(@PathVariable TipoReceita tipoReceita) {
        return receitaRepository.findByTipoReceita(tipoReceita);
    }

    @GetMapping(path = "/buscarPorPeriodo")
    public ResponseEntity<List<Receitas>> listarPorPeriodo(@RequestParam Date dataInicial,
                                                           @RequestParam Date dataFinal) {
        return new ResponseEntity<List<Receitas>>(receitaRepository
                .findBetweenDates(dataInicial, dataFinal), HttpStatus.OK);

    }


}
