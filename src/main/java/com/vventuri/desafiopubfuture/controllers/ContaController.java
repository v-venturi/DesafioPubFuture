package com.vventuri.desafiopubfuture.controllers;

import com.vventuri.desafiopubfuture.entity.Conta;
import com.vventuri.desafiopubfuture.exceptions.FundsNotAvaliableException;
import com.vventuri.desafiopubfuture.repositories.ContaRepository;
import com.vventuri.desafiopubfuture.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * The type Conta controller.
 */
@RestController
@RequestMapping("contas")
@RequiredArgsConstructor
public class ContaController {
    @Autowired
    private final ContaService contaService;
    @Autowired
    private final ContaRepository contaRepository;
    DecimalFormat fmt = new DecimalFormat("#,##0.00");

    /**
     * Listar contas.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<Conta>> contas() {
        return ResponseEntity.ok(contaService.listarTodas());
    }
    /**
     * Listar por conta código.
     *
     * @param codConta the cod conta
     * @return the response entity
     */
    @GetMapping(path = "/{codConta}")
    public ResponseEntity<Conta> listarPorConta(@PathVariable Integer codConta) {
        return ResponseEntity.ok(contaService.procurarConta(codConta));
    }
    /**
     * Cadastrar nova conta.
     *
     * @param conta the conta
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Conta> cadastrar(@RequestBody Conta conta) {
        return new ResponseEntity<>(contaService.cadastrarConta(conta), HttpStatus.CREATED);
    }

    /**
     * Editar conta.
     *
     * @param codConta the cod conta
     * @param conta    the conta
     * @return the conta
     */
    @PutMapping(path = "/{codConta}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Conta editar(@PathVariable int codConta, @RequestBody Conta conta) {
        Conta conta1 = contaRepository.findById(codConta).get();
        BeanUtils.copyProperties(conta, conta1, "codConta");
        return contaRepository.save(conta1);
    }
    /**
     * Deletar conta.
     *
     * @param codConta the cod conta
     */
    @DeleteMapping(path = "/{codConta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta (@PathVariable int codConta) {
        contaRepository.deleteById(codConta);
    }
    /**
     * Transferir saldo.
     *
     * @param contaOrigem      the conta origem
     * @param contaDestino     the conta destino
     * @param valorTransferido the valor transferido
     * @return the list
     * @throws FundsNotAvaliableException the funds not avaliable exception
     */
    @PutMapping(path = "transferir/{contaOrigem}/{contaDestino}/{valorTransferido}")
    @ResponseStatus(HttpStatus.ACCEPTED)
     public List<Object> transferirSaldo(@PathVariable(value = "contaOrigem") @NotNull Conta contaOrigem,
                                        @PathVariable(value = "contaDestino") @NotNull Conta contaDestino,
                                        @PathVariable(value = "valorTransferido") Double valorTransferido)
            throws FundsNotAvaliableException {
        contaService.sacar(contaOrigem.getCodConta(), valorTransferido);
        contaService.depositar(contaDestino.getCodConta(), valorTransferido);
        String conta = "Contas Atualizadas";
        String valor = "Valor Transferido com sucesso";
        return Arrays.asList(conta, contaOrigem, contaDestino, valor, valorTransferido);
    }

    /**
     * Listar totais conta list.
     *
     * @param saldo              the saldo
     * @param saldoTotal         the saldo total
     * @param saldoPoupanca      the saldo poupanca
     * @param totalPoupanca      the total poupanca
     * @param saldoContaCorrente the saldo conta corrente
     * @param totalContaCorrente the total conta corrente
     * @param saldoCarteira      the saldo carteira
     * @param totalCarteira      the total carteira
     * @return the list
     */
    @GetMapping(path = "/totalContas")
    @ResponseBody
    public List<Object> listarTotaisConta(String saldo, Double saldoTotal,
                                          String saldoPoupanca, Double totalPoupanca,
                                          String saldoContaCorrente, Double totalContaCorrente,
                                          String saldoCarteira, Double totalCarteira) {
        saldo = "Saldo Total";
        saldoContaCorrente = "Total Conta Corrente";
        saldoCarteira = "Total Carteira";
        saldoPoupanca = "Total Poupança";
        return Arrays.asList(saldoContaCorrente, fmt.format(contaRepository.getSaldoContaCorrente()),
                saldoCarteira, fmt.format(contaRepository.getSaldoCarteira()),
                saldoPoupanca, fmt.format(contaRepository.getSaldoPoupanca()),
                saldo, fmt.format(contaRepository.getSaldoTotal()));
    }
}


