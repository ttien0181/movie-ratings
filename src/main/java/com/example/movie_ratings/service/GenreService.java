package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.genre.GenreRequest;
import com.example.movie_ratings.dto.genre.GenreResponse;
import com.example.movie_ratings.entity.Genre;

import java.util.List;

public interface GenreService {

    GenreResponse create(GenreRequest request);

    GenreResponse getById(Long id);

    List<GenreResponse> getAll();

    GenreResponse update(Long id, GenreRequest request);

    void delete(Long id);
}

