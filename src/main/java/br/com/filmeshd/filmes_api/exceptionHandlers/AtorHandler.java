package br.com.filmeshd.filmes_api.exceptionHandlers;

import br.com.filmeshd.filmes_api.controller.AtorController;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroCampo;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroResposta;
import br.com.filmeshd.filmes_api.exceptions.atorExceptions.AtorDuplicadoException;
import br.com.filmeshd.filmes_api.exceptions.atorExceptions.AtorNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice(assignableTypes =  AtorController.class)
public class AtorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta AtorDuplicadoExceptionHandle(AtorDuplicadoException exception) {
        return ErroResposta.conflito(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception) {
        List<ErroCampo> fieldErrorList = exception.getFieldErrors().stream()
                .map(fieldError -> new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage())).toList();

        return ErroResposta.conteudoNaoProcessavel("ERRO DE VALIDAÇÃO!", fieldErrorList);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta AtorNaoEncontradoExceptionHandle(AtorNaoEncontradoException exception) {
        return ErroResposta.naoEncontrado(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta MethodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException exception) {
        return ErroResposta.respostaPadrao("Houve um problema no preenchimento da requisição!");
    }
}
