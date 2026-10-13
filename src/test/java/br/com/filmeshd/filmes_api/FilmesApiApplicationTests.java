package br.com.filmeshd.filmes_api;

import br.com.filmeshd.filmes_api.model.Diretor;
import br.com.filmeshd.filmes_api.service.DiretorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class FilmesApiApplicationTests {
	@Autowired
	DiretorService diretorService;

	@Test
	public void inserirDiretorTest() {
		Diretor diretor = new Diretor("Zack Snyder", LocalDate.of(1989, 10, 20));

		diretorService.inserirDiretor(diretor);

	}

}
