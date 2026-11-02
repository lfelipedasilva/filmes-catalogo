package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import br.com.filmeshd.filmes_api.validator.FilmeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final FilmeValidator filmeValidator;

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
}
