package lucas.online_bank.testes.services;

import lucas.online_bank.service.ContaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContaServiceTest {
    @Autowired
    ContaService contaService;
    @Test
    public void contextLoads() {
        Assertions.assertNotNull(contaService);
    }
//    @Test
//    void testCria() {
//        assertEquals(,contaService.cria(new TransacaoDTO().setValor(new BigDecimal(30));
//    }
//    void testBuscaTransacoes(){
//
//    }
}
