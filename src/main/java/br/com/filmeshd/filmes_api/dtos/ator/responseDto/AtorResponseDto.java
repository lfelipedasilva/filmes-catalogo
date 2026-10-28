package br.com.filmeshd.filmes_api.dtos.ator.responseDto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AtorResponseDto {
    @Id
    @NotBlank
    private UUID id;
    @NotBlank
    private String nome;
    @NotNull
    @Min(8)
    private Integer idade;

    public AtorResponseDto(UUID id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public AtorResponseDto() {
    }
}
