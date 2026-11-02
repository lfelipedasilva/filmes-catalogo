package br.com.filmeshd.filmes_api.repository;

import br.com.filmeshd.filmes_api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, UUID> {
    Optional<Filme> findByNome(String nome);

}
