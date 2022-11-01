package lucas.online_bank.testes.model;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.model.TransacaoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransacaoDTOTest {

    TransacaoDTO transacaoDTO = new TransacaoDTO();
    Conta contaMock = new Conta();

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(transacaoDTO);
    }
    @Test
    public void TestGettersAndSetters() {
        transacaoDTO.setValor(new BigDecimal(-600));
        transacaoDTO.setConta(contaMock.getIdConta());
        transacaoDTO.setDataTransacao(LocalDate.of(2022, 7, 14));
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals(new BigDecimal(-600), transacaoDTO.getValor()),
                () -> assertEquals(contaMock.getIdConta(), transacaoDTO.getConta()),
                () -> assertEquals(LocalDate.of(2022, 7, 14), transacaoDTO.getDataTransacao())
        );
    }
}
