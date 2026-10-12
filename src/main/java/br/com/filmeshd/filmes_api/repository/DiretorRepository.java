package br.com.filmeshd.filmes_api.repository;

import br.com.filmeshd.filmes_api.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiretorRepository extends JpaRepository<UUID, Diretor> {
}
