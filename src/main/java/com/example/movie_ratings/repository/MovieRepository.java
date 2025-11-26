package com.example.movie_ratings.repository;

import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String keyword);

    @Query("""
    SELECT m FROM Movie m
    JOIN m.movieGenres mg
    WHERE mg.genre.id = :genreId
""")
    List<Movie> findByGenreId(Long genreId);


}
