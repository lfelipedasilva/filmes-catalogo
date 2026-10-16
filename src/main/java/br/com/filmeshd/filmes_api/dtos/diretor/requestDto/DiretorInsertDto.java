package br.com.filmeshd.filmes_api.dtos.diretor.requestDto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DiretorInsertDto {
    @NotBlank(message = "nome invalido!")
    @Size(min = 1, max = 100)
    private String nome;
    @NotNull(message = "data de nascimento invalida!")
    @Past
    private LocalDate dataNascimento;

    public DiretorInsertDto(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}
