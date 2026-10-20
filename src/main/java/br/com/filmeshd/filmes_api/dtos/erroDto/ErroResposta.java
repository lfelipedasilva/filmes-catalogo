package br.com.filmeshd.filmes_api.dtos.erroDto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(Integer status, String mensagem, List<ErroCampo> erroCampo) {

    public static ErroResposta respostaPadrao(String mensagem) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

    public static ErroResposta conteudoNaoProcessavel(String mensagem, List<ErroCampo> erros) {
        return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(), mensagem, erros);
    }

    public static ErroResposta naoEncontrado(String mensagem) {
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }

}
