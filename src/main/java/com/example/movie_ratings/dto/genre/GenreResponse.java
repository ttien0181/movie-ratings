package com.example.movie_ratings.dto.genre;

import lombok.Data;

@Data
public class GenreResponse {
    private Long id;
    private String name;
    private String description;
}
