package br.com.filmeshd.filmes_api.model;

import br.com.filmeshd.filmes_api.enums.ClassIndicativa;
import br.com.filmeshd.filmes_api.enums.Genero;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "filmes")
@EntityListeners(AuditingEntityListener.class)
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;
    @Column(name = "sinopse")
    private String sinopse;
    @Column(name = "data_estreia")
    private LocalDate dataEstreia;
    @Column(name = "minutagem")
    private Integer minutagem;

    @Column(name = "classificacao_indicativa")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ClassIndicativa classIndicativa;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Genero genero;

    @Column(name = "nota_publico")
    private BigDecimal notaPublico = null;
    @Column(name = "data_registro")
    @CreatedDate
    private LocalDateTime dataRegistro;
    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ator_filme",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private List<Ator> listaDeAtores;

    public Filme(String nome, Diretor diretor, String sinopse, LocalDate dataEstreia, Integer minutagem, ClassIndicativa classIndicativa, Genero genero) {
        this.nome = nome;
        this.diretor = diretor;
        this.sinopse = sinopse;
        this.dataEstreia = dataEstreia;
        this.minutagem = minutagem;
        this.classIndicativa = classIndicativa;
        this.genero = genero;
    }

    public Filme() {
    }
}
