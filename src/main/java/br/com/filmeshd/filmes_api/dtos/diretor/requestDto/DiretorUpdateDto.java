package br.com.filmeshd.filmes_api.dtos.diretor.requestDto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class DiretorUpdateDto {
    @Id
    @NotNull
    private UUID id;
    private String nome;
    @Past
    private LocalDate dataNascimento;

    public DiretorUpdateDto(UUID id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public DiretorUpdateDto() {
    }
}
