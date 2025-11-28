package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.comment.CommentRequest;
import com.example.movie_ratings.dto.comment.CommentResponse;
import com.example.movie_ratings.entity.Comment;

import java.util.List;

public interface CommentService {

    CommentResponse create(CommentRequest request);

    CommentResponse update(Long id, CommentRequest request);

    void delete(Long id);

    List<CommentResponse> getByReviewId(Long reviewId);

    List<CommentResponse> getByUserId(Long userId);
}

