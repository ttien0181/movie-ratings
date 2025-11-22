package com.example.movie_ratings.dto.movie;

import lombok.Data;

@Data
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Float rating;
    private Integer totalRate;
    private Long genreId;
    private String createdAt;
    private String actors;
}
