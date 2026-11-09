package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeUpdateDto;
import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;
import br.com.filmeshd.filmes_api.mappers.FilmeMapper;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import br.com.filmeshd.filmes_api.specs.FilmeSpecs;
import br.com.filmeshd.filmes_api.validator.FilmeValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static br.com.filmeshd.filmes_api.specs.FilmeSpecs.*;


@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final FilmeValidator filmeValidator;
    private final FilmeMapper filmeMapper;

    public void saveFilme(Filme filme) {
        filmeValidator.validarInsercaoOuAtualizacao(filme);
        filmeRepository.save(filme);
    }

    public Filme obterDetalhesFilme(UUID id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        filmeValidator.validarExistenciaFilme(filme.get());
        return filme.get();
    }

    public void deletarFilme(UUID id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        filmeValidator.validarExistenciaFilme(filme.get());
        filmeRepository.deleteById(id);
    }

    @Transactional
    public void atualizarFilme(FilmeUpdateDto filme) {
        Optional<Filme> filmeOptional = filmeRepository.findById(filme.getId());
        filmeValidator.validarInsercaoOuAtualizacao(filmeMapper.toUpdateEntity(filme));

        List<UUID> list = filmeRepository.findAllAtoresByFilmeId(filme.getId()).stream()
                .map(Ator::getId).toList();

        list.forEach(filme.getIdAtores()::add);

        boolean todosExistem = list.stream().allMatch(filmeRepository::existsById);

        if (todosExistem) {
            filme.setIdAtores(list);
        }

        filmeMapper.atualizarFilmeIgnorandoNullValues(filme, filmeOptional.get());
    }



    public List<Filme> pesquisarFilme(String nome, String nomeDiretor, LocalDate dataEstreia,
                                               Integer minutagem, ClassIndicativa classIndicativa, Genero genero, BigDecimal notaPublico) {

        Specification<Filme> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (nome != null) {
            specs = specs.and(nomeLike(nome));
        }
        if (nomeDiretor != null) {
            specs = specs.and(nomeDiretorLike(nomeDiretor));
        }
        if (dataEstreia != null) {
            specs = specs.and(anoEstreiaEqual(dataEstreia.getYear()));
        }
        if (minutagem != null) {
            specs = specs.and(minutagemEqual(minutagem));
        }
        if (classIndicativa != null) {
            specs = specs.and(classIndicativaEqual(classIndicativa));
        }
        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }
        if (notaPublico != null) {
            specs = specs.and(notaPublicoEqual(notaPublico));
        }

        return filmeRepository.findAll(specs);
    }
}
