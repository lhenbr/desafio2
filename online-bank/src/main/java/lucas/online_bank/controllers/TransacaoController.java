package lucas.online_bank.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    @PostMapping("/depositar")
    public ResponseEntity<Long> depositar(@RequestBody @Valid TransacaoDTO transacaoDTO){
        //Validar conta(Bloqueda ou inativa)
        ContaDTO conta = contaService.get(transacaoDTO.getConta());
        BigDecimal novoSaldo = conta.getSaldo().add(transacaoDTO.getValor());
        conta.setSaldo(novoSaldo);
        contaService.update(conta.getIdConta(),conta);
        return new ResponseEntity<>(transacaoService.create(transacaoDTO), HttpStatus.CREATED);
    }
    @PostMapping("/sacar")
    public ResponseEntity<Long> sacar(@RequestBody @Valid TransacaoDTO transacaoDTO){
        //Validar conta(Bloqueda ou inativa)
        ContaDTO conta = contaService.get(transacaoDTO.getConta());
        BigDecimal novoSaldo = conta.getSaldo().add(transacaoDTO.getValor());
        conta.setSaldo(novoSaldo);
        contaService.update(conta.getIdConta(),conta);
        return new ResponseEntity<>(transacaoService.create(transacaoDTO), HttpStatus.CREATED);
    }
    @GetMapping("/extrato/{idConta}")
    //validar conta
    public ResponseEntity<List<TransacaoDTO>> MostraExtratoEntreDatas(@PathVariable final Long idConta, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataInicial, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataFinal){
        return ResponseEntity.ok(transacaoService.BuscaExtrato(idConta,dataInicial,dataFinal));
    }
}
