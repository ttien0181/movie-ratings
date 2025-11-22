package com.example.movie_ratings.service;

import com.example.movie_ratings.dto.review.ReviewRequest;
import com.example.movie_ratings.dto.review.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse create(ReviewRequest request);

    ReviewResponse getById(Long id);

    List<ReviewResponse> getAll();

    ReviewResponse update(Long id, ReviewRequest request);

    void delete(Long id);

    void updateMovieRating(Long movieId);

    List<ReviewResponse> getByMovieId(Long movieId);

    List<ReviewResponse> getByUserId(Long userId);


}
