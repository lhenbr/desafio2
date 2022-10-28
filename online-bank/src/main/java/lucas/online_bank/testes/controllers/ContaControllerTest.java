package lucas.online_bank.testes.controllers;

import lucas.online_bank.controllers.ContaContoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContaControllerTest {
    @Autowired
    ContaContoller contaController;
    @DisplayName("Teste ContaController")
    @Test
    public void contextLoads() {
        Assertions.assertNotNull(contaController);
    }
}
