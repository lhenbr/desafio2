package lucas.online_bank;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.service.PessoaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lucas.online_bank.repos.PessoaRepository;
import lucas.online_bank.repos.TransacaoRepository;
import lucas.online_bank.repos.ContaRepository;
import lucas.online_bank.domain.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootApplication
public class OnlineBankApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OnlineBankApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(PessoaRepository pessoaRepository, TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
        return (args) -> {
            Pessoa pessoa =Pessoa.builder()
                    .cpf("11111111111")
                    .nome("Lucas")
                    .dataNascimento(LocalDate.of(1993,8,30))
                    .build();
            Conta conta = Conta.builder()
                    .pessoa(pessoa)
                    .saldo(BigDecimal.valueOf(600))
                    .tipoConta(Long.valueOf(0))
                    .flagAtivo(true)
                    .limiteSaqueDiario(BigDecimal.valueOf(200))
                    .dataCriacao(LocalDate.now())
                    .build();
            pessoaRepository.save(pessoa);
            contaRepository.save(conta);
        };
    }
}
