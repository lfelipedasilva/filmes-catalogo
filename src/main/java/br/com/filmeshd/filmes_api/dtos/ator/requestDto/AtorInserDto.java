package br.com.filmeshd.filmes_api.dtos.ator.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class AtorInserDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String nome;
    @NotNull
    @Min(8)
    private Integer idade;

    public AtorInserDto(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public AtorInserDto() {
    }
}
