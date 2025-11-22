package com.example.movie_ratings.repository;

import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenreId(Long genreId);


    List<Movie> findByGenre(Genre genre);

    List<Movie> findByTitleContainingIgnoreCase(String keyword);

}
