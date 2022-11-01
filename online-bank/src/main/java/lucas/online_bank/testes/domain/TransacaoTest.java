package lucas.online_bank.testes.domain;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.model.TransacaoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransacaoTest {

    TransacaoDTO transacaoDTO = new TransacaoDTO();
    Conta conta = new Conta();


    @Test
    public void contextLoads() {
        Assertions.assertNotNull(transacaoDTO);
    }

    @Test
    public void TestGettersAndSetters() {
        transacaoDTO.setValor(new BigDecimal(-600));
        transacaoDTO.setConta(conta.getIdConta());
        transacaoDTO.setDataTransacao(LocalDate.of(2022, 7, 14));
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals(new BigDecimal(-600), transacaoDTO.getValor()),
                () -> assertEquals(conta.getIdConta(), transacaoDTO.getConta()),
                () -> assertEquals(LocalDate.of(2022, 7, 14), transacaoDTO.getDataTransacao())
        );
    }
}
