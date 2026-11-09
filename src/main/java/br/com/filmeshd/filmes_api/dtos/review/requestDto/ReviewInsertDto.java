package br.com.filmeshd.filmes_api.dtos.review.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ReviewInsertDto {
    @NotNull
    private UUID filmeId;
    @NotNull
    private BigDecimal nota;
    private String comentario;

    public ReviewInsertDto(UUID filme, BigDecimal nota, String comentario) {
        this.filmeId = filme;
        this.nota = nota;
        this.comentario = comentario;
    }

    public ReviewInsertDto() {
    }

}
