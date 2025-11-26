package com.example.movie_ratings.dto.movie;

import com.example.movie_ratings.dto.genre.GenreResponse;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Float rating;
    private Integer totalRate;
    private String createdAt;
    private String actors;
    private String posterUrl;

    private List<GenreResponse> genres;
}

