package br.com.filmeshd.filmes_api.mappers;

import br.com.filmeshd.filmes_api.dtos.review.requestDto.ReviewInsertDto;
import br.com.filmeshd.filmes_api.dtos.review.responseDto.ReviewResponseDto;
import br.com.filmeshd.filmes_api.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReviewMapper.class)
public interface ReviewMapper {
    Review toEntity(ReviewInsertDto reviewInsertDto);

    @Mapping(source = "filme.nome", target = "filmeNome")
    ReviewResponseDto toResponse(Review review);

}
