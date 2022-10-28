package lucas.online_bank.testes.controllers;

import lucas.online_bank.controllers.PessoaController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    PessoaController pessoaController;
    @DisplayName("Teste PessoaController")
    @Test
    public void contextLoads() {
        Assertions.assertNotNull(pessoaController);
    }
}
