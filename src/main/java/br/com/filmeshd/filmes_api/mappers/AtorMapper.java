package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.ator.requestDto.AtorInserDto;
import br.com.filmeshd.filmes_api.dtos.ator.responseDto.AtorResponseDto;
import br.com.filmeshd.filmes_api.model.Ator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtorMapper {

    Ator toEntity(AtorInserDto ator);

    AtorResponseDto toResponseDto(Ator ator);
}
