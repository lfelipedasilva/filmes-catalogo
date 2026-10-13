package br.com.filmeshd.filmes_api.exceptions.geralExceptions;

public class IdNaoExisteException extends RuntimeException {
    public IdNaoExisteException(String message) {
        super(message);
    }
}
