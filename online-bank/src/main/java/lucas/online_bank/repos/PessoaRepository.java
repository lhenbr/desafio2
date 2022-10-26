package lucas.online_bank.repos;

import lucas.online_bank.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByCpfIgnoreCase(String cpf);

}
