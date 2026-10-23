package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorUpdateDto;
import br.com.filmeshd.filmes_api.dtos.diretor.responseDto.DiretorResponseDto;
import br.com.filmeshd.filmes_api.model.Diretor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DiretorMapper {
    Diretor toEntity(DiretorInsertDto diretor);

    DiretorResponseDto toDto(Diretor diretor);

    Diretor toUpdateEntity(DiretorUpdateDto diretorUpdateDto);

    DiretorUpdateDto toUpdateDto(Diretor diretor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarDiretorIgnorandoNullValues(DiretorUpdateDto diretorUpdateDto, @MappingTarget Diretor diretor);
}
