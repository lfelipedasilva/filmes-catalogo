package br.com.filmeshd.filmes_api.exceptions.atorExceptions;

public class AtorNaoEncontradoException extends RuntimeException {
    public AtorNaoEncontradoException(String message) {
        super(message);
    }
}
