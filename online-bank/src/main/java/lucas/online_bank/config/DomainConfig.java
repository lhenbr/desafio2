package lucas.online_bank.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("lucas.online_bank.domain")
@EnableJpaRepositories("lucas.online_bank.repos")
@EnableTransactionManagement
public class DomainConfig {
}
