package lucas.online_bank.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PessoaDTO {

    private Long idPessoa;

    @NotNull
    @Size(max = 255)
    private String nome;

    @NotNull
    @Size(max = 11, min = 11)
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

}
