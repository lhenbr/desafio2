package lucas.online_bank.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Valid;

import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.model.TransacaoDTO;
import lucas.online_bank.service.ContaService;
import lucas.online_bank.service.TransacaoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/transacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final ContaService contaService;

    public TransacaoController(final TransacaoService transacaoService, final ContaService contaService) {
        this.transacaoService = transacaoService;
        this.contaService = contaService;
    }
    @PostMapping("/realizarTransacao")
    public ResponseEntity<Object> realizarTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO){
        ContaDTO conta = contaService.busca(transacaoDTO.getConta());
        if(!conta.getFlagAtivo()){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A conta esta Bloqueada");
        }
        if(transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0 && conta.getSaldo().compareTo(transacaoDTO.getValor().abs()) < 0){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A conta não possui saldo para está transação");
        }
        BigDecimal novoSaldo = conta.getSaldo().add(transacaoDTO.getValor());
        conta.setSaldo(novoSaldo);
        contaService.modifica(conta.getIdConta(),conta);
        return new ResponseEntity<>(transacaoService.cria(transacaoDTO), HttpStatus.CREATED);
    }
    @GetMapping("/extrato/{idConta}")
    public ResponseEntity<Object> MostraExtratoEntreDatas(@PathVariable final Long idConta, @RequestParam(required = false,defaultValue = "+999999999-12-31") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataInicial, @RequestParam(required = false,defaultValue = "-999999999-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataFinal){
        ContaDTO conta = contaService.busca(idConta);
        return conta.getFlagAtivo()  ?  ResponseEntity.ok(transacaoService.BuscaTransacoes(idConta,dataInicial,dataFinal)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A conta esta Bloqueada");
    }
}
