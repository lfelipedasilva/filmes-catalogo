package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import br.com.filmeshd.filmes_api.validator.DiretorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository diretorRepository;
    private final DiretorValidator diretorValidator;

    public void  inserirDiretor(Diretor diretor) {
        diretorValidator.validarDiretor(diretor);
        diretorRepository.save(diretor);
    }
}
