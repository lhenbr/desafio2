package lucas.online_bank.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lucas.online_bank.model.ContaDTO;


@Entity
@Getter
@Setter
public class Transacao {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long idTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta;

}
