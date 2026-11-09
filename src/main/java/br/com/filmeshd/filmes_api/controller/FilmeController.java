package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeInsertDto;
import br.com.filmeshd.filmes_api.dtos.filme.requestDto.FilmeUpdateDto;
import br.com.filmeshd.filmes_api.dtos.filme.responseDto.FilmeResponseDto;
import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;
import br.com.filmeshd.filmes_api.mappers.FilmeMapper;
import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.service.FilmeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {
    private final FilmeService filmeService;
    private final FilmeMapper filmeMapper;

    @PostMapping
    public ResponseEntity<Void> inserirFilme(@RequestBody @Valid FilmeInsertDto filmeInsertDto) {
        Filme filme = filmeMapper.toEntity(filmeInsertDto);
        filmeService.saveFilme(filme);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(filme.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDto> obterFilmePorId(@PathVariable UUID id) {
        Filme filme = filmeService.obterDetalhesFilme(id);
        return ResponseEntity.ok(filmeMapper.toFilmeResponseDto(filme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable UUID id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarFilme(@RequestBody @Valid FilmeUpdateDto filmeUpdateDto) {
        filmeService.atualizarFilme(filmeUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponseDto>> pesquisarFilmes(@RequestParam(required = false) String nome,
                                                       @RequestParam(required = false) String nomeDiretor,
                                                       @RequestParam(required = false)LocalDate dataEstreia, @RequestParam(required = false) Integer minutagem,
                                                       @RequestParam(required = false) ClassIndicativa classIndicativa,
                                                       @RequestParam(required = false)Genero genero,
                                                       @RequestParam(required = false)BigDecimal notaPublico) {

        List<FilmeResponseDto> list = filmeService.pesquisarFilme(nome, nomeDiretor, dataEstreia, minutagem, classIndicativa, genero, notaPublico).stream()
                .map(filmeMapper::toFilmeResponseDto).toList();

        return ResponseEntity.ok(list);
    }
}
