package lucas.online_bank.testes.services;

import lucas.online_bank.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    PessoaService pessoaService;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(pessoaService);
    }
//    @Test
//    void testCria() {
//        assertEquals(pessoaService.cria(new TransacaoDTO().setValor(new BigDecimal(30));
//    }
//    buscaTodos
//    cria
//    modifica
//    exclui

}
