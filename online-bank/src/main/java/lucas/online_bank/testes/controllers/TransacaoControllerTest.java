package lucas.online_bank.testes.controllers;

import lucas.online_bank.controllers.TransacaoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TransacaoControllerTest {

    @Autowired
    TransacaoController transacaoController;

    @DisplayName("Teste TransacaoController")

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(transacaoController);
    }
//    @Test
//    void
}
