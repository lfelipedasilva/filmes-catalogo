package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.exceptions.diretorExceptions.DiretorNaoEncontradoException;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import br.com.filmeshd.filmes_api.validator.DiretorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository diretorRepository;
    private final DiretorValidator diretorValidator;

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


}
