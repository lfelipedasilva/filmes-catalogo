package br.com.filmeshd.filmes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FilmesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApiApplication.class, args);
	}

}
