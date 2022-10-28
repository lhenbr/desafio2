package lucas.online_bank.testes;

import lucas.online_bank.OnlineBankApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class onlineBankTest {
    @Autowired
    OnlineBankApplication onlineBankApplication;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(onlineBankApplication);
    }
}
