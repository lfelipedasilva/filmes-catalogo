package br.com.filmeshd.filmes_api.service;

import br.com.filmeshd.filmes_api.model.Filme;
import br.com.filmeshd.filmes_api.model.Review;
import br.com.filmeshd.filmes_api.repository.FilmeRepository;
import br.com.filmeshd.filmes_api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FilmeRepository filmeRepository;
    private Double divisaoIndice;

    private Double calcularMedia(Review review, List<Review> reviews) {
        Double soma = 0.0;
        for (Review review1 : reviews) {
            soma += review1.getNota().doubleValue();
        }
        if (review != null) {
            divisaoIndice = 1.0;
            soma += review.getNota().doubleValue();
        }

        Double media;
        if (reviews.size() == 0) {
            if (review == null) {
                media = 0.0;
                return media;
            }
            media = review.getNota().doubleValue();
        }


        media = soma / (reviews.size() + divisaoIndice);
        return media;
    }


    public void inserirReview(Review review, UUID filmeId) {
        Filme filme = filmeRepository.findById(filmeId).orElseThrow();
        review.setFilme(filme);
        List<Review> list = reviewRepository.findAllByFilmeId(filmeId);
        filme.setNotaPublico(BigDecimal.valueOf(calcularMedia(review, list)));

        filmeRepository.save(filme);
        reviewRepository.save(review);
    }

    public void deletarReview(UUID id) {
        divisaoIndice = 0.0;
        Review review = reviewRepository.findById(id).orElseThrow();
        Filme filme = filmeRepository.findById(review.getFilme().getId()).orElseThrow();
        reviewRepository.delete(review);
        List<Review> list = reviewRepository.findAllByFilmeId(filme.getId());
        filme.setNotaPublico(BigDecimal.valueOf(calcularMedia(null, list)));
        filmeRepository.save(filme);
    }

    public List<Review> pesquisarReviewsPeloFilmeID(UUID filmeId) {
        return reviewRepository.findAllByFilmeId(filmeId);
    }

}
