package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorUpdateDto;
import br.com.filmeshd.filmes_api.dtos.diretor.responseDto.DiretorResponseDto;
import br.com.filmeshd.filmes_api.mappers.DiretorMapper;
import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.service.DiretorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<DiretorResponseDto> obterDiretorPorId(@PathVariable UUID id){
        Diretor diretor = diretorService.obterDetalhesPorId(id);
        return ResponseEntity.ok(diretorMapper.toDto(diretor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable UUID id){
        diretorService.deletarDiretor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DiretorResponseDto> atualizarDiretor(@RequestBody @Valid DiretorUpdateDto diretorUpdateDto) {
        Diretor diretor = diretorService.atualizarDiretor(diretorMapper.toUpdateEntity(diretorUpdateDto));
        return ResponseEntity.ok(diretorMapper.toDto(diretor));
    }

}
