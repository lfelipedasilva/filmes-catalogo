package br.com.filmeshd.filmes_api.exceptions.filmeExceptions;

public class FilmeDuplicadoException extends RuntimeException {
    public FilmeDuplicadoException(String message) {
        super(message);
    }
}
