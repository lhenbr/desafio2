package lucas.online_bank.testes.model;

import lucas.online_bank.domain.Pessoa;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.model.TransacaoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContaDTOTest {

    ContaDTO contaDTO = new ContaDTO();

    Pessoa pessoaMock = new Pessoa();

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(contaDTO);
    }

    @Test
    public void TestGettersAndSetters() {
        contaDTO.setSaldo(new BigDecimal(600));
        contaDTO.setLimiteSaqueDiario(new BigDecimal(200));
        contaDTO.setFlagAtivo(true);
        contaDTO.setTipoConta(1L);
        contaDTO.setDataCriacao(LocalDate.of(2022, 3, 8));
        contaDTO.setPessoa(pessoaMock.getIdPessoa());
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals(new BigDecimal(600), contaDTO.getSaldo()),
                () -> assertEquals(new BigDecimal(200), contaDTO.getLimiteSaqueDiario()),
                () -> assertEquals(true, contaDTO.getFlagAtivo()),
                () -> assertEquals(1L, contaDTO.getTipoConta()),
                () -> assertEquals((LocalDate.of(2022, 3, 8)), contaDTO.getDataCriacao()),
                () -> assertEquals(pessoaMock.getIdPessoa(), contaDTO.getPessoa())
        );
    }
}
