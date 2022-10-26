package lucas.online_bank.repos;

import lucas.online_bank.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta, Long> {
}
