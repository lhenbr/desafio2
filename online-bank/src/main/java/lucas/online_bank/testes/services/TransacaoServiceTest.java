package lucas.online_bank.testes.services;

import lucas.online_bank.model.TransacaoDTO;
import lucas.online_bank.service.TransacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransacaoServiceTest {

@Autowired
TransacaoService transacaoService;

    TransacaoDTO transaçaoDTO = new TransacaoDTO();


    @DisplayName("Teste TransacaoService")

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(transacaoService);
    }
//    @Test
//    void testCria() {
//        assertEquals(transacaoService.cria(new TransacaoDTO().setValor(new BigDecimal(30));
//    }
//   void buscaTransacoes(){
//
//   }
}
