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
//import com.example.neu.dto.appointment.AppointmentResponse;
//import com.example.neu.dto.auditlog.AuditLogResponse;
//import com.example.neu.dto.caseDto.CaseRequest;
//import com.example.neu.dto.caseDto.CaseResponse;
//import com.example.neu.dto.casecasetag.CaseCaseTagResponse;
//import com.example.neu.dto.casefile.CaseFileResponse;
//import com.example.neu.dto.caseperson.CasePersonResponse;
//import com.example.neu.dto.casetag.CaseTagRequest;
//import com.example.neu.dto.casetag.CaseTagResponse;
//import com.example.neu.dto.category.CategoryRequest;
//import com.example.neu.dto.category.CategoryResponse;
//import com.example.neu.dto.person.PersonRequest;
//import com.example.neu.dto.person.PersonResponse;
//import com.example.neu.dto.question.QuestionResponse;
//import com.example.neu.entity.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ValueMapper {

    ValueMapper MAPPER = Mappers.getMapper(ValueMapper.class);

    // ============================
    // USER
    // ============================
//    UserResponse convertToUserResponse(User user);
    User convertToUser(UserRequest request);
//    List<UserResponse> convertToUserResponseList(List<User> users);
//
//    // ============================
//    // GENRE
//    // ============================
//    GenreResponse convertToGenreResponse(Genre genre);
//    Genre convertToGenre(GenreRequest request);
//    List<GenreResponse> convertToGenreResponseList(List<Genre> list);
//
//    // ============================
//    // MOVIE
//    // ============================
//    @Mapping(source = "genre.id", target = "genreId")
//    MovieResponse convertToMovieResponse(Movie movie);
//
//    @Mapping(source = "genreId", target = "genre.id")
//    Movie convertToMovie(MovieRequest request);
//
//    List<MovieResponse> convertToMovieResponseList(List<Movie> movies);

    GenreResponse convertToGenreResponse(Genre genre);

    Genre convertToGenre(GenreRequest request);

    List<GenreResponse> convertToGenreResponseList(List<Genre> genres);


    // MOVIE
    @Mapping(source = "genre.id", target = "genreId")
    MovieResponse convertToMovieResponse(Movie movie);

    @Mapping(source = "genreId", target = "genre.id")
    Movie convertToMovie(MovieRequest req);

    List<MovieResponse> convertToMovieResponseList(List<Movie> movieList);

    //
//    // ============================
//    // REVIEW
//    // ============================
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "movie.id", target = "movieId")
//    ReviewResponse convertToReviewResponse(Review review);
//
//    @Mapping(source = "userId", target = "user.id")
//    @Mapping(source = "movieId", target = "movie.id")
//    Review convertToReview(ReviewRequest request);
//
//    List<ReviewResponse> convertToReviewResponseList(List<Review> reviews);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "movie.id", target = "movieId")
    ReviewResponse convertToReviewResponse(Review review);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "movieId", target = "movie.id")
    Review convertToReview(ReviewRequest reviewRequest);

    List<ReviewResponse> convertToReviewResponseList(List<Review> reviews);

//
//    // ============================
//    // COMMENT
//    // ============================
    // Convert Entity → Response DTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "review.id", target = "reviewId")
    CommentResponse convertToCommentResponse(com.example.movie_ratings.entity.Comment comment);

    // Convert Request DTO → Entity
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "reviewId", target = "review.id")
    com.example.movie_ratings.entity.Comment convertToComment(CommentRequest request);

    // Convert List<Entity> → List<ResponseDTO>
    List<CommentResponse> convertToCommentResponseList(List<Comment> comments);
//
//    @Mapping(source = "userId", target = "user.id")
//    @Mapping(source = "reviewId", target = "review.id")
//    Comment convertToComment(CommentRequest request);
//
//    List<CommentResponse> convertToCommentResponseList(List<Comment> comments);
}
