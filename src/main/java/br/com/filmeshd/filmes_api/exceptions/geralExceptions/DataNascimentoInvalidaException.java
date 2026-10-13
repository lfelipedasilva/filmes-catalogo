package br.com.filmeshd.filmes_api.exceptions.geralExceptions;

public class DataNascimentoInvalidaException extends RuntimeException {
    public DataNascimentoInvalidaException(String message) {
        super(message);
    }
}
