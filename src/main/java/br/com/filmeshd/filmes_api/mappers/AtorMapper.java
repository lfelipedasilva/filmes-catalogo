package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.ator.requestDto.AtorInserDto;
import br.com.filmeshd.filmes_api.dtos.ator.requestDto.AtorUpdateDto;
import br.com.filmeshd.filmes_api.dtos.ator.responseDto.AtorResponseDto;
import br.com.filmeshd.filmes_api.model.Ator;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AtorMapper {

    Ator toEntity(AtorInserDto ator);

    AtorResponseDto toResponseDto(Ator ator);

    Ator fromUpdateDTO(AtorUpdateDto ator);

    AtorUpdateDto toUpdateDto(Ator ator);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarAtorIgnoreNullValues(AtorUpdateDto atorDto, @MappingTarget Ator ator);
}
