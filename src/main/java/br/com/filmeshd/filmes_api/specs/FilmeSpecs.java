package br.com.filmeshd.filmes_api.specs;

import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;
import br.com.filmeshd.filmes_api.model.Filme;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class FilmeSpecs {

    public static Specification<Filme> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%"  + nome.toUpperCase() + "%");
    }

    public static Specification<Filme> nomeDiretorLike(String nomeDiretor) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("diretor").get("nome")), "%" + nomeDiretor.toUpperCase() + "%");
    }

    public static Specification<Filme> anoEstreiaEqual(Integer anoEstreia) {
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class, root.get("dataEstreia"), cb.literal("YYYY")), anoEstreia.toString());
    }

    public static Specification<Filme> minutagemEqual(Integer minutagem) {
        return (root, query, cb) ->
                cb.equal(root.get("minutagem"), minutagem);
    }

    public static Specification<Filme> classIndicativaEqual (ClassIndicativa classIndicativa) {
        return (root, query, cb) ->
                cb.equal(root.get("classIndicativa"), classIndicativa);
    }

    public static Specification<Filme> generoEqual(Genero genero) {
        return (root, query, cb) ->
                cb.equal(root.get("genero"), genero);
    }

    public static Specification<Filme> notaPublicoEqual(BigDecimal notaPublico) {
        return (root, query, cb) ->
                cb.equal(root.get("notaPublico"), notaPublico);
    }
 }
