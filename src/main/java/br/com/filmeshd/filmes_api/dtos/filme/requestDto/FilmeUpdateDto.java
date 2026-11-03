package br.com.filmeshd.filmes_api.dtos.filme.requestDto;

import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class FilmeUpdateDto {
    @NotNull
    UUID id;
    @NotBlank
    private String nome;
    @NotNull
    private UUID diretorId;
    @NotBlank
    private String sinopse;
    @NotNull
    private LocalDate dataEstreia;
    @NotNull
    @Positive
    private Integer minutagem;
    @NotNull
    private ClassIndicativa classIndicativa;
    @NotNull
    private Genero genero;
    @NotNull
    private List<UUID> idAtores;

    public FilmeUpdateDto(UUID id, String nome, UUID diretorId, String sinopse, LocalDate dataEstreia, Integer minutagem, ClassIndicativa classIndicativa, Genero genero, List<UUID> idAtores) {
        this.id = id;
        this.nome = nome;
        this.diretorId = diretorId;
        this.sinopse = sinopse;
        this.dataEstreia = dataEstreia;
        this.minutagem = minutagem;
        this.classIndicativa = classIndicativa;
        this.genero = genero;
        this.idAtores = idAtores;
    }

    public FilmeUpdateDto() {
    }
}
