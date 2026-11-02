package br.com.filmeshd.filmes_api.exceptions.filmeExceptions;

public class FilmeNaoEncontradoException extends RuntimeException {
    public FilmeNaoEncontradoException(String message) {
        super(message);
    }
}
