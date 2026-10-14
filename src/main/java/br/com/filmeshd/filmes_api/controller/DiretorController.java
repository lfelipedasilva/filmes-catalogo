package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
import br.com.filmeshd.filmes_api.mappers.DiretorMapper;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.service.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diretores")
@RequiredArgsConstructor
public class DiretorController {
    private final DiretorService diretorService;
    private final DiretorMapper diretorMapper;

    @PostMapping
    public void inserirDiretor(@RequestBody DiretorInsertDto diretorRequest){
        Diretor diretor = diretorMapper.toEntity(diretorRequest);
        diretorService.inserirDiretor(diretor);
    }
}
