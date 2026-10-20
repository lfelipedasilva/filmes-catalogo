package br.com.filmeshd.filmes_api.dtos.diretor.responseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DiretorResponseDto {
    @org.hibernate.validator.constraints.UUID
    @NotNull
    private UUID id;
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;

    public DiretorResponseDto(UUID id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public DiretorResponseDto() {
    }
}
