package br.com.filmeshd.filmes_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;
    @Column(name = "nota")
    private BigDecimal nota;
    @Column(name = "comentario")
    private String comentario;

    public Review(Filme filme, BigDecimal nota, String comentario) {
        this.filme = filme;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Review() {
    }
}
