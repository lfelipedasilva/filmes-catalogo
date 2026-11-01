package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.ator.requestDto.AtorInserDto;
import br.com.filmeshd.filmes_api.dtos.ator.requestDto.AtorUpdateDto;
import br.com.filmeshd.filmes_api.dtos.ator.responseDto.AtorResponseDto;
import br.com.filmeshd.filmes_api.mappers.AtorMapper;
import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.service.AtorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/atores")
@RequiredArgsConstructor
public class AtorController {
    private final AtorService atorService;
    private final AtorMapper atorMapper;

    @PostMapping
    public ResponseEntity<Void> inserirAtor(@RequestBody @Valid AtorInserDto atorDto) {
        Ator ator = atorMapper.toEntity(atorDto);
        atorService.inserirAtor(ator);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ator.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtorResponseDto> consultarAtor(@PathVariable UUID id) {
        Ator ator = atorService.buscarAtorPorId(id);
        AtorResponseDto atorResponseDto = atorMapper.toResponseDto(ator);
        return ResponseEntity.ok(atorResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable UUID id) {
        atorService.deletarAtor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<AtorResponseDto> atualizarAtor(@RequestBody @Valid AtorUpdateDto atorDto) {
        Ator ator = atorService.atualizarAtor(atorMapper.fromUpdateDTO(atorDto));
        return ResponseEntity.ok(atorMapper.toResponseDto(ator));
    }

    @GetMapping
    public ResponseEntity<List<AtorResponseDto>> consultarAtores(@RequestParam(required = false) String nome,
                                                                 @RequestParam(required = false) Integer idade) {
        List<AtorResponseDto> lista = atorService.pesquisarAtores(nome, idade).stream()
                .map(ator -> new AtorResponseDto(ator.getId(), ator.getNome(), ator.getIdade())).toList();
        return ResponseEntity.ok(lista);
    }
}
