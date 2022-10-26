package lucas.online_bank.repos;

import lucas.online_bank.domain.Conta;
import lucas.online_bank.domain.Transacao;
import lucas.online_bank.model.ContaDTO;
import lucas.online_bank.model.TransacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
List<Transacao> findByContaAndDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(Conta conta, LocalDate dataInicial, LocalDate dataFinal);
}
