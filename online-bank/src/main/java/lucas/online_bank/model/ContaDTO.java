package lucas.online_bank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContaDTO {

    private Long idConta;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "200")
    private BigDecimal saldo;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "51.08")
    @Min(0)
    private BigDecimal limiteSaqueDiario;

    @NotNull
    private Boolean flagAtivo;

    @NotNull
    private Long tipoConta;

    @NotNull
    private LocalDate dataCriacao;

    @NotNull
    private Long pessoa;

}
