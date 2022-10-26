package lucas.online_bank.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/conta", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaContoller {

    private final ContaService contaService;

    public ContaContoller(final ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("consultarTodas")
    public ResponseEntity<List<ContaDTO>> getAllContas() {
        return ResponseEntity.ok(contaService.findAll());
    }

    @PostMapping("/criar")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> CriarConta(@RequestBody @Valid final ContaDTO contaDTO) {
        return new ResponseEntity<>(contaService.create(contaDTO), HttpStatus.CREATED);
    }
    @GetMapping("/ConsultarSaldo/{idConta}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Object>MostrarSaldo(@PathVariable final Long idConta) {
        ContaDTO conta = (contaService.get(idConta));
        return conta.getFlagAtivo()  ? ResponseEntity.ok(conta.getSaldo()) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A conta esta Bloqueada");
    }
    @PutMapping("/bloquear/{idConta}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> BloquearConta(@PathVariable final Long idConta){
        ContaDTO contaDTO = contaService.get(idConta);
        if(contaDTO.getFlagAtivo() == false){
            return new ResponseEntity<>(
                    "Sua conta já está bloqueada",
                    HttpStatus.CONFLICT
            );
        }
        contaDTO.setFlagAtivo(false);
        contaService.update(idConta,contaDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/desbloquear/{idConta}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Object> DesbloquearConta(@PathVariable final Long idConta){
        ContaDTO contaDTO = contaService.get(idConta);
        if(contaDTO.getFlagAtivo() == true) {
            return new ResponseEntity<>(
                    "Sua conta já está desbloqueada",
                    HttpStatus.CONFLICT
            );
        }
        contaDTO.setFlagAtivo(true);
        contaService.update(idConta,contaDTO);
        return ResponseEntity.ok().build();
    }




}
