package lucas.online_bank.testes.domain;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Pessoa;
import lucas.online_bank.service.ContaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    Pessoa pessoa = new Pessoa();

    Set<Conta> contas = new HashSet<Conta>();

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(pessoa);
    }

    @Test
    public void TestGettersAndSetters() {
        pessoa.setNome("Lucas");
        pessoa.setCpf("114000000");
        pessoa.setDataNascimento(LocalDate.of(1993, 8, 30));
        pessoa.setPessoaContas(contas);
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals("Lucas", pessoa.getNome()),
                () -> assertEquals("114000000", pessoa.getCpf()),
                () -> assertEquals(LocalDate.of(1993, 8, 30), pessoa.getDataNascimento()),
                () -> assertEquals(contas,pessoa.getPessoaContas())
        );
    }
}

