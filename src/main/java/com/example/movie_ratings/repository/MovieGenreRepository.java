package com.example.movie_ratings.repository;

import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.MovieGenre;
import com.example.movie_ratings.entity.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {

    void deleteByMovie(Movie movie);

}

