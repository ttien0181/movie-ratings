package com.example.movie_ratings.dto.movie;

import lombok.Data;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private Integer releaseYear;
    private Long genreId;
    private String actors;
}
