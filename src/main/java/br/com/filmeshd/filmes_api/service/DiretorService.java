package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.exceptions.diretorExceptions.DiretorNaoEncontradoException;
import br.com.filmeshd.filmes_api.mappers.DiretorMapper;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import br.com.filmeshd.filmes_api.validator.DiretorValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository diretorRepository;
    private final DiretorValidator diretorValidator;
    private final DiretorMapper diretorMapper;

    public void  inserirDiretor(Diretor diretor) {
        diretorValidator.validarDiretor(diretor);
        diretorRepository.save(diretor);
    }

    public Diretor obterDetalhesPorId(UUID id) {
        Optional<Diretor> diretor = diretorRepository.findById(id);

        if (diretor.isPresent()) {
            return diretor.get();
        }
        throw new DiretorNaoEncontradoException("O ID informado esta incorreto ou não existe!");
    }

    public void deletarDiretor(UUID id) {
        Boolean existe = !diretorRepository.existsById(id);
        if (existe) {
            throw new DiretorNaoEncontradoException("Diretor Inexistente!");
        }
        diretorRepository.deleteById(id);
    }

    @Transactional
    public Diretor atualizarDiretor(Diretor diretor) {
        Optional<Diretor> diretoReal = diretorRepository.findById(diretor.getId());
        diretorValidator.validarDiretor(diretor);
        diretorMapper.atualizarDiretorIgnorandoNullValues(diretorMapper.toUpdateDto(diretor), diretoReal.get());
        return diretor;
    }

    public List<Diretor> obterDiretores(String nome, LocalDate dataNascimento) {
        Diretor diretor = new Diretor(nome, dataNascimento);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).
                withIgnoreNullValues();

        Example<Diretor> diretorExample = Example.of(diretor, exampleMatcher);

        List<Diretor> lista = diretorRepository.findAll(diretorExample);

        if (lista.isEmpty()) {
            throw new DiretorNaoEncontradoException("Nao foi encontrado nenhum diretor com os dados digitados!");
        }

        return lista;
    }
}
