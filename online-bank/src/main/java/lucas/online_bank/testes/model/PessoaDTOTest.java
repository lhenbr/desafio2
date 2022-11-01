package lucas.online_bank.testes.model;

import lucas.online_bank.model.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaDTOTest {


    PessoaDTO pessoaDTO = new PessoaDTO();


    @Test
    public void contextLoads() {
        Assertions.assertNotNull(pessoaDTO);
    }

    @Test
    public void TestGettersAndSetters() {
        pessoaDTO.setNome("Lucas");
        pessoaDTO.setCpf("11400000011");
        pessoaDTO.setDataNascimento(LocalDate.of(1993, 8, 30));
        assertAll("retorna todos os campos de acordo com os setters",
                () -> assertEquals("Lucas", pessoaDTO.getNome()),
                () -> assertEquals("11400000011", pessoaDTO.getCpf()),
                () -> assertEquals(LocalDate.of(1993, 8, 30), pessoaDTO.getDataNascimento())
        );
    }
}

