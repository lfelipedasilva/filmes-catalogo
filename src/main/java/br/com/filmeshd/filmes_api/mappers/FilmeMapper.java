package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeInsertDto;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = AtorMapper.class)
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
}
