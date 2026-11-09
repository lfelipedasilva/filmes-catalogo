package br.com.filmeshd.filmes_api.controller;

import br.com.filmeshd.filmes_api.dtos.review.requestDto.ReviewInsertDto;
import br.com.filmeshd.filmes_api.dtos.review.responseDto.ReviewResponseDto;
import br.com.filmeshd.filmes_api.mappers.ReviewMapper;
import br.com.filmeshd.filmes_api.model.Review;
import br.com.filmeshd.filmes_api.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<Void> publicarReview(@RequestBody @Valid ReviewInsertDto reviewInsertDto) {
        Review review = reviewMapper.toEntity(reviewInsertDto);
        reviewService.inserirReview(review, reviewInsertDto.getFilmeId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReview(@PathVariable UUID id) {
        reviewService.deletarReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{filmeId}")
    public ResponseEntity<List<ReviewResponseDto>> pesquisarReviewsPeloFilmeID(@PathVariable UUID filmeId) {
        List<ReviewResponseDto> list = reviewService.pesquisarReviewsPeloFilmeID(filmeId).stream()
                .map(review -> reviewMapper.toResponse(review)).toList();
        return ResponseEntity.ok(list);
    }
}
