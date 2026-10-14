package br.com.filmeshd.filmes_api.dtos.diretor.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DiretorInsertDto {
    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;
    @NotBlank
    private LocalDate dataNascimento;

    public DiretorInsertDto(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}
