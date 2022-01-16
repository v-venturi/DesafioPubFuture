package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.entity.Despesas;
import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.repositories.DespesaRepository;
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
 * The type Despesa controller.
 */
@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController {

    @Autowired
    private final DespesaRepository despesaRepository;
    private final ContaService contaService;
    @Autowired
    private final ContaRepository contaRepository;
    DecimalFormat formatter = new DecimalFormat("#,##0.00");

    /**
     * Despesas list.
     *
     * @return the list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Despesas> despesas() {
        return despesaRepository.findAll();
    }
      /**
     * Cadastrar despesas.
     * Cada Despesa criada debita valor da conta releacionada — somente se a data de pagamento for igual ou anterior
     * a data atual e somente se houver saldo disponível, caso contrário lança exceção.
     *
     * @param despesas the despesas
     * @return the despesas
     * @throws FundsNotAvaliableException the funds not avaliable exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Despesas cadastrarDespesa(@RequestBody Despesas despesas) throws FundsNotAvaliableException {
        despesaRepository.save(despesas);
        if (despesas.getDataPagamento().before(Date.valueOf(LocalDate.now()))) {
            Conta conta1 = contaRepository.findById(despesas.getConta().getCodConta()).get();
            contaService.sacar(conta1.getCodConta(), despesas.getValor());
        }
        return despesas;
    }

    /**
     * Editar despesas.
     *
     * @param codDespesa the cod despesa
     * @param despesas   the despesas
     * @return the despesas
     */
    @PutMapping(path = "/{codDespesa}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Despesas editar(@PathVariable int codDespesa, @RequestBody Despesas despesas) {
        Despesas despesa1 = despesaRepository.findById(codDespesa).get();
        despesas.setDespesaId(despesa1.getDespesaId());
        BeanUtils.copyProperties(despesas, despesa1, "codDespesa");
        return despesaRepository.saveAndFlush(despesa1);
    }
    /**
     * Deletar despesa.
     *
     * @param codDespesa the cod despesa
     */
    @DeleteMapping(path = "/{codDespesa}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarDespesa(@PathVariable int codDespesa) {
        despesaRepository.deleteById(codDespesa);
    }
    /**
     * Listar despesas por tipo list.
     *
     * @param tipoDespesa the tipo despesa
     * @return the list
     */
    @GetMapping(path = "/{tipoDespesa}") // ignoreCase implantado via componente conversor de enum
    @ResponseStatus(HttpStatus.OK)
    public List<Despesas> listarDespesasPorTipo(@PathVariable TipoDespesa tipoDespesa) {
        return despesaRepository.findByTipoDespesa(tipoDespesa);
    }
    /**
     * Listar despesas por periodo.
     *
     * @param dataInicial the data inicial
     * @param dataFinal   the data final
     * @return the response entity
     */
    @GetMapping(path = "/buscarPorPeriodo")
    public ResponseEntity<List<Despesas>> listarPorPeriodo(@RequestParam Date dataInicial,
                                                           @RequestParam Date dataFinal) {
        return new ResponseEntity<List<Despesas>>(despesaRepository
                .findBetweenDates(dataInicial, dataFinal), HttpStatus.OK);
    }
    /**
     * Saldo Total despesas list.
     *
     * @return the list
     */
    @GetMapping(path = "/totalDespesas")
    @ResponseStatus(HttpStatus.OK)
    public List<Serializable> listarSaldoDespesas() {
        double soma = 0.0;
        String totalDespesa = "Total de depesas";
        for (Despesas despesa : despesas()) {
            soma += despesa.getValor();
        }
        return Arrays.asList(totalDespesa, formatter.format(soma));
    }


}
