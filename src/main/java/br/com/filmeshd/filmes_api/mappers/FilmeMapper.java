package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeInsertDto;
import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeUpdateDto;
import br.com.filmeshd.filmes_api.dtos.filme.responseDto.FilmeResponseDto;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {AtorMapper.class, DiretorMapper.class})
public abstract class FilmeMapper {
    @Autowired
    AtorRepository atorRepository;

    @Autowired
    DiretorRepository diretorRepository;

    protected List<Ator> transformarIdParaAtor(List<UUID> idAtores) {
        return idAtores.stream()
                .map(id -> {
                    Optional<Ator> atorOptional = atorRepository.findById(id);
                    return atorOptional.get();
                }).toList();
    }

    @Mapping(target = "listaDeAtores", expression = "java(transformarIdParaAtor(filmeInsertDto.getIdAtores()))")
    @Mapping(target = "diretor", expression = "java(diretorRepository.findById(filmeInsertDto.getDiretorId()).get())")
    public abstract Filme toEntity(FilmeInsertDto filmeInsertDto);

    @Mapping(target = "listaDeAtores", expression = "java(transformarIdParaAtor(filmeUpdateDto.getIdAtores()))")
    @Mapping(target = "diretor", expression = "java(diretorRepository.findById(filmeUpdateDto.getDiretorId()).get())")
    public abstract Filme toUpdateEntity(FilmeUpdateDto filmeUpdateDto);

    @Mapping(source = "listaDeAtores", target = "elenco")
    public abstract FilmeResponseDto toFilmeResponseDto(Filme filme);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "listaDeAtores", expression = "java(transformarIdParaAtor(filmeUpdateDto.getIdAtores()))")
    @Mapping(target = "diretor", expression = "java(diretorRepository.findById(filmeUpdateDto.getDiretorId()).get())")
    public abstract void atualizarFilmeIgnorandoNullValues(FilmeUpdateDto filmeUpdateDto, @MappingTarget Filme filme);

}
