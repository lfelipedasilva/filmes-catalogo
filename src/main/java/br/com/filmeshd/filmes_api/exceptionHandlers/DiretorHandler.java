package br.com.filmeshd.filmes_api.exceptionHandlers;

import br.com.filmeshd.filmes_api.controller.DiretorController;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroCampo;
import br.com.filmeshd.filmes_api.dtos.erroDto.ErroResposta;
import br.com.filmeshd.filmes_api.exceptions.diretorExceptions.DiretorExistenteException;
import br.com.filmeshd.filmes_api.exceptions.diretorExceptions.DiretorNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice(assignableTypes = DiretorController.class)
public class DiretorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta DiretorExistenteExceptionHandle(DiretorExistenteException exception) {
        return ErroResposta.conflito(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErroCampo> erroCampos = fieldErrors.stream().map(erros -> new ErroCampo(erros.getField(), erros.getDefaultMessage())).toList();
        return ErroResposta.conteudoNaoProcessavel("ERRO DE VALIDAÇÃO!", erroCampos);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta HttpMessageNotReadableExceptionHandle(HttpMessageNotReadableException exception) {
        return ErroResposta.respostaPadrao("Houve algum erro no preenchimento da requisição!");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta DiretorNaoEncontradoExceptionHandle(DiretorNaoEncontradoException exception) {
        return ErroResposta.naoEncontrado(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta MethodArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException exception) {
        return ErroResposta.conteudoNaoProcessavel("Algum dado nao foi processado corretamente!", List.of());
    }


}
