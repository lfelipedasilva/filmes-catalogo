package br.com.filmeshd.filmes_api.exceptions.diretorExceptions;

public class DiretorNaoEncontradoException extends RuntimeException {
    public DiretorNaoEncontradoException(String message) {
        super(message);
    }
}
