package br.com.filmeshd.filmes_api.validator;

import br.com.filmeshd.filmes_api.exceptions.filmeExceptions.FilmeDuplicadoException;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilmeValidator {
    private final FilmeRepository filmeRepository;

    public void validarFilme(Filme filme) {
        if (existeFilme(filme)) {
            throw new FilmeDuplicadoException("Já existe um filme com os dados a serem cadastrados ou atualizados!");
        }
    }

    private Boolean existeFilme(Filme filme) {
        Optional<Filme> filmeOptional = filmeRepository.findByNome(filme.getNome());
        if (filme.getId() == null) {
            return filmeOptional.isPresent();
        }

        return filmeOptional.isPresent() && !filme.getId().equals(filmeOptional.get().getId());
    }
}
