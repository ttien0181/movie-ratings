package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.external.ExternalMovieDTO;

public interface ExternalMovieService {
    ExternalMovieDTO fetchMovieFromApi(Long movieId);
}
