package br.com.filmeshd.filmes_api.validator;

import br.com.filmeshd.filmes_api.exceptions.atorExceptions.AtorDuplicadoException;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtorValidator {
    private final AtorRepository repository;

    public void validarAtor(Ator ator) {
        if (existeAtor(ator)) {
            throw new AtorDuplicadoException("O Ator a ser inserido ou atualizado já existe!");
        }
    }

    private boolean existeAtor(Ator ator) {
        Optional<Ator> atorAchado = repository.findByNome(ator.getNome());

        if (ator == null) {
            return atorAchado.isPresent();
        }

        return atorAchado.isPresent() && !atorAchado.get().equals(ator);
    }
}
