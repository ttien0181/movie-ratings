package com.example.movie_ratings.dto.comment;

import lombok.Data;

@Data
public class CommentRequest {
    private Long userId;
    private Long reviewId;
    private String content;
}
