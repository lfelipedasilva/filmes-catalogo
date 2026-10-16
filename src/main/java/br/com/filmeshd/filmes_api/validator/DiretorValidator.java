package br.com.filmeshd.filmes_api.validator;


import br.com.filmeshd.filmes_api.exceptions.diretorExceptions.DiretorExistenteException;
import br.com.filmeshd.filmes_api.exceptions.geralExceptions.DataNascimentoInvalidaException;
import br.com.filmeshd.filmes_api.exceptions.geralExceptions.IdNaoExisteException;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiretorValidator {
    private final DiretorRepository diretorRepository;

    public void validarDiretor(Diretor diretor) {
        if (existeDiretor(diretor)) {
            throw new DiretorExistenteException("ja existe um Diretor com os dados a serem inseridos ou atualizados!");
        }

        if (dataNascimentoValido(diretor)) {
            throw new DataNascimentoInvalidaException("para o diretor ser inserido, é necessario que sua data de nascimento seja inferior a 2008!");
        }
    }

    private Boolean existeDiretor(Diretor diretor) {
        Optional<Diretor> diretorAchado = diretorRepository.findByNome(diretor.getNome());

        if (diretor.getId() == null) {
            return diretorAchado.isPresent();
        }

        if (!diretorRepository.existsById(diretor.getId())) {
            throw new IdNaoExisteException("O id digitado esta incorreto ou nao existe!");
        }

        return diretorAchado.isPresent() && !diretor.getId().equals(diretorAchado.get().getId());
    }

    private Boolean dataNascimentoValido(Diretor diretor) {
        if (diretor.getDataNascimento().getYear() >= 2008) {
            return true;
        }
        return false;
    }
}
