package br.com.filmeshd.filmes_api.dtos.review.responseDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ReviewResponseDto {
    private UUID id;
    private String filmeNome;
    private BigDecimal nota;
    private String comentario;

    public ReviewResponseDto(UUID id, String filmeNome, BigDecimal nota, String comentario) {
        this.id = id;
        this.filmeNome = filmeNome;
        this.nota = nota;
        this.comentario = comentario;
    }

    public ReviewResponseDto() {
    }
}
