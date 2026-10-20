package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
import br.com.filmeshd.filmes_api.dtos.diretor.responseDto.DiretorResponseDto;
import br.com.filmeshd.filmes_api.model.Diretor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiretorMapper {
    Diretor toEntity(DiretorInsertDto diretor);

    DiretorResponseDto toDto(Diretor diretor);
}
