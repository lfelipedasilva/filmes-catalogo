package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.exceptions.atorExceptions.AtorNaoEncontradoException;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import br.com.filmeshd.filmes_api.validator.AtorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtorService {
    private final AtorRepository atorRepository;
    private final AtorValidator atorValidator;

    public void inserirAtor(Ator ator) {
        atorValidator.validarAtor(ator);
        atorRepository.save(ator);
    }

    public Ator buscarAtorPorId(UUID id) {
        Optional<Ator> ator = atorRepository.findById(id);
        if (ator.isPresent()) {
            return ator.get();
        }

        throw new AtorNaoEncontradoException("O id digitado está incorreto ou não existe!");
    }

    public void deletarAtor(UUID id) {
        Optional<Ator> ator = atorRepository.findById(id);

        if (ator.isEmpty()) {
            throw new AtorNaoEncontradoException("Ator não encontrado!");
        }

        atorRepository.delete(ator.get());

    }
}
