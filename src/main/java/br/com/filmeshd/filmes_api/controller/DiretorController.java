package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
import br.com.filmeshd.filmes_api.mappers.DiretorMapper;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.service.DiretorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/diretores")
@RequiredArgsConstructor
public class DiretorController {
    private final DiretorService diretorService;
    private final DiretorMapper diretorMapper;

    @PostMapping
    public ResponseEntity<Void> inserirDiretor(@RequestBody @Valid DiretorInsertDto diretorRequest){
        Diretor diretor = diretorMapper.toEntity(diretorRequest);
        diretorService.inserirDiretor(diretor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(diretor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
