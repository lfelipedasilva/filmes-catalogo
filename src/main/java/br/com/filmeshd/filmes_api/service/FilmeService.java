package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeUpdateDto;
import br.com.filmeshd.filmes_api.mappers.FilmeMapper;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import br.com.filmeshd.filmes_api.validator.FilmeValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
}
