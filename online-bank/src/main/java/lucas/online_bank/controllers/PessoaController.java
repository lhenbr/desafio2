package lucas.online_bank.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import lucas.online_bank.model.PessoaDTO;
import lucas.online_bank.service.PessoaService;
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
@RequestMapping(value = "/api/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(final PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/consultarTodas")
    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping("/consultar/{idPessoa}")
    public ResponseEntity<PessoaDTO> getPessoa(@PathVariable final Long idPessoa) {
        return ResponseEntity.ok(pessoaService.get(idPessoa));
    }

    @PostMapping("/criar")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPessoa(@RequestBody @Valid final PessoaDTO pessoaDTO) {
        return new ResponseEntity<>(pessoaService.create(pessoaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{idPessoa}")
    public ResponseEntity<Void> updatePessoa(@PathVariable final Long idPessoa,
            @RequestBody @Valid final PessoaDTO pessoaDTO) {
        pessoaService.update(idPessoa, pessoaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{idPessoa}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePessoa(@PathVariable final Long idPessoa) {
        pessoaService.delete(idPessoa);
        return ResponseEntity.noContent().build();
    }

}
