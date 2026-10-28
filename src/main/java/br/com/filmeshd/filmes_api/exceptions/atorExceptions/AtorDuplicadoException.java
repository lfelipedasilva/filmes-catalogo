package br.com.filmeshd.filmes_api.exceptions.atorExceptions;

public class AtorDuplicadoException extends RuntimeException {
    public AtorDuplicadoException(String message) {
        super(message);
    }
}
