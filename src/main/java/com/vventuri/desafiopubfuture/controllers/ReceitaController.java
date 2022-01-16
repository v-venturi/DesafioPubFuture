package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.entity.Receitas;
import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.repositories.ReceitaRepository;
import com.vventuri.desafiopubfuture.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * The type Receita controller.
 */
@RestController
@RequestMapping("receitas")
@RequiredArgsConstructor
public class ReceitaController {

    @Autowired
    private final ReceitaRepository receitaRepository;
    @Autowired
    private final ContaRepository contaRepository;
    @Autowired
    private final ContaService contaService;
    /**
     * The Formatter.
     */
    DecimalFormat formatter = new DecimalFormat("#,##0.00");

    /**
     * Receitas list.
     *
     * @return the list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Receitas> receitas() {
        return receitaRepository.findAll();
    }

    /**
     * Cadastrar receitas.
     *
     * @param receitas the receitas
     * @return the receitas
     * @throws FundsNotAvaliableException the funds not avaliable exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receitas cadastrarReceitas(@RequestBody Receitas receitas) throws FundsNotAvaliableException {

        Conta conta1 = contaRepository.findById(receitas.getConta().getCodConta()).get();
        if (receitas.getDataRecebimento().before(Date.valueOf(LocalDate.now()))) {
            contaService.depositar(conta1.getCodConta(), receitas.getValor());
        } else {
            contaService.depositar(conta1.getCodConta(), 0.0);
        }
        return receitas;

    }

    /**
     * Editar receitas.
     *
     * @param codReceita the cod receita
     * @param receitas   the receitas
     * @return the receitas
     */
    @PutMapping(path = "/{codReceita}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Receitas editar(@PathVariable int codReceita, @RequestBody Receitas receitas) {
        Receitas receita1 = receitaRepository.findById(codReceita).get();
        receitas.setReceitaId(receita1.getReceitaId());
        BeanUtils.copyProperties(receitas, receita1, "codReceita");
        return receitaRepository.saveAndFlush(receita1);
    }

    /**
     * Deletar receita.
     *
     * @param codReceita the cod receita
     */
    @DeleteMapping(path = "/{codReceita}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarReceita(@PathVariable int codReceita) {
        receitaRepository.deleteById(codReceita);
    }

    /**
     * Listar receitas por tipo list.
     *
     * @param tipoReceita the tipo receita
     * @return the list
     */
    @GetMapping(path = "/{tipoReceita}") // ignoreCase implantado via componente conversor de enum
    @ResponseStatus(HttpStatus.OK)
    public List<Receitas> listarReceitasPorTipo(@PathVariable TipoReceita tipoReceita) {
        return receitaRepository.findByTipoReceita(tipoReceita);
    }

    /**
     * Listar receitas por periodo.
     *
     * @param dataInicial the data inicial
     * @param dataFinal   the data final
     * @return the response entity
     */
    @GetMapping(path = "/buscarPorPeriodo")
    public ResponseEntity<List<Receitas>> listarPorPeriodo(@RequestParam Date dataInicial,
                                                           @RequestParam Date dataFinal) {
        return new ResponseEntity<List<Receitas>>(receitaRepository
                .findBetweenDates(dataInicial, dataFinal), HttpStatus.OK);
    }
    /**
     * Saldo Total receitas list.
     *
     * @return the list
     */
    @GetMapping(path = "/totalReceitas")
    @ResponseStatus(HttpStatus.OK)
    public List<Serializable> totalReceitas() {
        double total = 0;
        String saldoReceitas = "Saldo total Receitas";
        for (Receitas receita : receitas()) {
            total += receita.getValor();
        }
        return Arrays.asList(saldoReceitas, formatter.format(total));
    }
}