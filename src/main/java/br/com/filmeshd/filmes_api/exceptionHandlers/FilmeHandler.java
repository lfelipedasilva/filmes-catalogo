package br.com.filmeshd.filmes_api.exceptionHandlers;

import br.com.filmeshd.filmes_api.controller.FilmeController;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroCampo;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroResposta;
import br.com.filmeshd.filmes_api.exceptions.filmeExceptions.FilmeDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = FilmeController.class)
public class FilmeHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta FilmeDuplicadoExceptionHandle(FilmeDuplicadoException exception) {
        return ErroResposta.conflito(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception) {
        List<ErroCampo> fieldErrorList = exception.getFieldErrors().stream().map(e -> new ErroCampo(e.getField(), e.getDefaultMessage())).toList();
        return ErroResposta.conteudoNaoProcessavel("Ocorreu um erro no preenchimento da requisição!", fieldErrorList);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta HttpMessageNotReadableExceptionHandle(HttpMessageNotReadableException exception) {
        return ErroResposta.respostaPadrao("Não foi possível interpretar os dados enviados na requisição.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta MethodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException exception) {
        return ErroResposta.respostaPadrao("URL INVALIDA!");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta NoSuchElementExceptionHandle(NoSuchElementException exception) {
        return ErroResposta.naoEncontrado(exception.getMessage());
    }
}
