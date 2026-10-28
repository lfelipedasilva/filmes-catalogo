package br.com.filmeshd.filmes_api.repository;

import br.com.filmeshd.filmes_api.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AtorRepository extends JpaRepository<Ator, UUID> {
    Optional<Ator> findByNome(String nome);

}
