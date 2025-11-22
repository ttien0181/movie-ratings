package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.movie.MovieRequest;
import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieResponse create(MovieRequest request);

    MovieResponse getById(Long id);

    List<MovieResponse> getAll();

    MovieResponse update(Long id, MovieRequest request);

    void delete(Long id);

    List<MovieResponse> search(String keyword);

    List<MovieResponse> getByGenreId(Long genreId);

}

