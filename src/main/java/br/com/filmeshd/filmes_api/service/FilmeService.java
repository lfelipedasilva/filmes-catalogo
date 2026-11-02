package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import br.com.filmeshd.filmes_api.validator.FilmeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final FilmeValidator filmeValidator;

    public void saveFilme(Filme filme) {
        filmeValidator.validarFilme(filme);
        filmeRepository.save(filme);
    }
}
