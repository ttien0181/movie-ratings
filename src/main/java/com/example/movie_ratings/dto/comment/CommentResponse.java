package com.example.movie_ratings.dto.comment;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private Long reviewId;
    private String content;
    private String createdAt;
}
