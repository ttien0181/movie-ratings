package com.example.movie_ratings.dto.review;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long userId;
    private Long movieId;
    private Integer rating;
    private String content;
}
