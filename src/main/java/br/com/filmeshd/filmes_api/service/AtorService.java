package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.exceptions.atorExceptions.AtorNaoEncontradoException;
import br.com.filmeshd.filmes_api.mappers.AtorMapper;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.repository.AtorRepository;
import br.com.filmeshd.filmes_api.validator.AtorValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtorService {
    private final AtorRepository atorRepository;
    private final AtorValidator atorValidator;
    private final AtorMapper atorMapper;

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

    @Transactional
    public Ator atualizarAtor(Ator ator) {
        Optional<Ator> atorAtual = atorRepository.findById(ator.getId());
        if (atorAtual.isEmpty()) {
            throw new AtorNaoEncontradoException("O id digitado nao existe no banco de dados!");
        }
        atorValidator.validarAtor(ator);
        atorMapper.atualizarAtorIgnoreNullValues(atorMapper.toUpdateDto(ator), atorAtual.get());
        return ator;
    }

    public List<Ator> pesquisarAtores(String nome, Integer idade) {
        Ator ator =  new Ator();
        ator.setNome(nome);
        ator.setIdade(idade);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Ator> example = Example.of(ator, exampleMatcher);
        List<Ator> lista = atorRepository.findAll(example);

        if (lista.isEmpty()) {
            throw new AtorNaoEncontradoException("Nenhum Ator encontrado!");
        }
        return lista;
    }
}
