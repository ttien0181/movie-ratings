package com.example.movie_ratings.dto.movie;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private Integer releaseYear;
    private List<Long> genreIds;   // danh sách genre
    private String actors;
    private String posterUrl;
}
