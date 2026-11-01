package br.com.filmeshd.filmes_api.dtos.ator.requestDto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AtorUpdateDto {
    @Id
    @NotNull
    private UUID id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String nome;
    @NotNull
    @Min(8)
    private Integer idade;

    public AtorUpdateDto(UUID id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public AtorUpdateDto() {
    }
}
