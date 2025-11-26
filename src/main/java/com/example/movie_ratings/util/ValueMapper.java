package com.example.movie_ratings.util;

import com.example.movie_ratings.dto.comment.CommentRequest;
import com.example.movie_ratings.dto.comment.CommentResponse;
import com.example.movie_ratings.dto.genre.GenreRequest;
import com.example.movie_ratings.dto.genre.GenreResponse;
import com.example.movie_ratings.dto.movie.MovieRequest;
import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.dto.review.ReviewRequest;
import com.example.movie_ratings.dto.review.ReviewResponse;
import com.example.movie_ratings.dto.user.UserRequest;
import com.example.movie_ratings.entity.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ValueMapper {

    ValueMapper MAPPER = Mappers.getMapper(ValueMapper.class);

    // USER
    User convertToUser(UserRequest request);

    // GENRE
    GenreResponse convertToGenreResponse(Genre genre);
    Genre convertToGenre(GenreRequest request);
    List<GenreResponse> convertToGenreResponseList(List<Genre> genres);

    // MOVIE
    @Mapping(target = "genres", ignore = true)   // xử lý bằng AfterMapping
    MovieResponse convertToMovieResponse(Movie movie);

    List<MovieResponse> convertToMovieResponseList(List<Movie> movies);

    // sau khi mapping movie (bo truong genres) thi se map thu cong genres
    @AfterMapping
    default void enrichGenres(Movie movie, @MappingTarget MovieResponse response) {
        List<GenreResponse> genres = movie.getMovieGenres()
                .stream()
                .map(mg -> convertToGenreResponse(mg.getGenre()))
                .toList();

        response.setGenres(genres);

        // convert time
        if (movie.getCreatedAt() != null) {
            response.setCreatedAt(movie.getCreatedAt().toString());
        }
    }

    // REVIEW
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "movie.id", target = "movieId")
    ReviewResponse convertToReviewResponse(Review review);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "movieId", target = "movie.id")
    Review convertToReview(ReviewRequest request);

    List<ReviewResponse> convertToReviewResponseList(List<Review> reviews);

    // COMMENT
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "review.id", target = "reviewId")
    CommentResponse convertToCommentResponse(Comment comment);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "reviewId", target = "review.id")
    Comment convertToComment(CommentRequest request);

    List<CommentResponse> convertToCommentResponseList(List<Comment> comments);
}
