package br.com.filmeshd.filmes_api.validator;

import br.com.filmeshd.filmes_api.exceptions.filmeExceptions.FilmeDuplicadoException;
import br.com.filmeshd.filmes_api.exceptions.filmeExceptions.FilmeNaoEncontradoException;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilmeValidator {
    private final FilmeRepository filmeRepository;

    public void validarInsercaoOuAtualizacao(Filme filme) {
        if (filmeDuplicado(filme)) {
            throw new FilmeDuplicadoException("Já existe um filme com os dados a serem cadastrados ou atualizados!");
        }
    }

    public void validarExistenciaFilme(Filme filme) {
        if (filme == null) {
            throw new FilmeNaoEncontradoException("o ID digitado esta incorreto ou não existe!");
        }
    }

    private Boolean filmeDuplicado(Filme filme) {
        Optional<Filme> filmeOptional = filmeRepository.findByNome(filme.getNome());
        if (filme.getId() == null) {
            return filmeOptional.isPresent();
        }

        return filmeOptional.isPresent() && !filme.getId().equals(filmeOptional.get().getId());
    }

}

