package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeInsertDto;
import br.com.filmeshd.filmes_api.mappers.FilmeMapper;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
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
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {
    private final FilmeRepository filmeRepository;
    private final FilmeMapper filmeMapper;

    @PostMapping
    public ResponseEntity<Void> inserirFilme(@RequestBody @Valid FilmeInsertDto filmeInsertDto) {
        Filme filme = filmeMapper.toEntity(filmeInsertDto);
        filmeRepository.save(filme);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
