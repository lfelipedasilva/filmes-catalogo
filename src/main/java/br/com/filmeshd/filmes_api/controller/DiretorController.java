package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.diretor.requestDto.DiretorInsertDto;
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


    /*
    amanha: implementar
    obterDetalhes, para pesquisar um diretor especifico pelo ID
    pesquisar para pesquisar uma lista de diretores com dados especificos como por exemplo nomes semelhantes Exemplo: Sam, Sam Winters, Jim Sam etc...
    atualizar Diretor
    deletar diretor

    para isso sera necessario criar metodos no service, criar os endpoint no controller, provavelmente criar mais Dtos e tambem mexer no controllerAdvice, para amanham sera isso
    como ja to começando a me lembrar denovo do codigo, acredito que eu consiga fazer tudo isso amanham sim
     */
}
