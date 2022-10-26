package lucas.online_bank.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;

import lombok.*;
import lucas.online_bank.model.PessoaDTO;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

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
    private Long idConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal limiteSaqueDiario;

    @Column(nullable = false)
    private Boolean flagAtivo;

    @Column(nullable = false)
    private Long tipoConta;

    @Column(nullable = false)
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "conta")
    private Set<Transacao> contaTransacaos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}
