package br.com.filmeshd.filmes_api.repository;

import br.com.filmeshd.filmes_api.model.Ator;
import br.com.filmeshd.filmes_api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID>, JpaSpecificationExecutor<Filme> {
    Optional<Filme> findByNome(String nome);

    @Query("SELECT a FROM Filme f JOIN f.listaDeAtores a WHERE f.id = :id")
    List<Ator> findAllAtoresByFilmeId(@Param("id") UUID id);

}
