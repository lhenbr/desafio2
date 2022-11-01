package lucas.online_bank.testes.domain;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Pessoa;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.service.ContaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContaTest {

    Conta conta = new Conta();
    Pessoa pessoaMock = new Pessoa(0l,"Lucas","114000000",LocalDate.of(1993, 8, 30), new HashSet<Conta>());
   Set<Transacao> transacaosMock = new HashSet<Transacao>();

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(conta);
    }
    @Test
    public void TestGettersAndSetters(){
        conta.setSaldo(new BigDecimal(600));
        conta.setLimiteSaqueDiario(new BigDecimal(200));
        conta.setFlagAtivo(true);
        conta.setTipoConta(1L);
        conta.setDataCriacao(LocalDate.of(2022, 3, 8));
        conta.setContaTransacaos(transacaosMock);
        conta.setPessoa(pessoaMock);
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals(new BigDecimal(600), conta.getSaldo()),
                () -> assertEquals(new BigDecimal(200), conta.getLimiteSaqueDiario()),
                () -> assertEquals(true, conta.getFlagAtivo()),
                () -> assertEquals(1L, conta.getTipoConta()),
                () -> assertEquals((LocalDate.of(2022, 3, 8)), conta.getDataCriacao()),
                () -> assertEquals(transacaosMock, conta.getContaTransacaos()),
                () -> assertEquals(pessoaMock, conta.getPessoa())
        );
    }
}
