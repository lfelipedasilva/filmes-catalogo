package br.com.filmeshd.filmes_api.exceptions.diretorExceptions;

public class DiretorExistenteException extends RuntimeException {
    public DiretorExistenteException(String message) {
        super(message);
    }
}
