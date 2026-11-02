package br.com.filmeshd.filmes_api.dtos.filme.responseDto;


import br.com.filmeshd.filmes_api.dtos.ator.responseDto.AtorResponseDto;
import br.com.filmeshd.filmes_api.dtos.diretor.responseDto.DiretorResponseDto;
import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FilmeResponseDto(
        UUID id,
        String nome,
        DiretorResponseDto diretor,
        String sinopse,
        LocalDate dataEstreia,
        Integer minutagem,
        ClassIndicativa classIndicativa,
        Genero genero,
        BigDecimal notaPublico,
        List<AtorResponseDto> elenco
) {

}
