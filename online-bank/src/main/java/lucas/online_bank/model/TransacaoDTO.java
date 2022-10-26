package lucas.online_bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransacaoDTO {

    private Long idTransacao;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "51.08")
    @Min(0)
    private BigDecimal valor;

    @NotNull
    private LocalDate dataTransacao;

    @NotNull
    private Long conta;

}
