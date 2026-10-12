package br.com.filmeshd.filmes_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "atores")
@EntityListeners(AuditingEntityListener.class)
public class Ator {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "idade")
    private Integer idade;
    @Column(name = "data_registro")
    @CreatedDate
    private LocalDate dataRegistro;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDate dataAtualizacao;

    public Ator(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Ator() {
    }
}
