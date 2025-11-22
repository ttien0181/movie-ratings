package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.review.ReviewRequest;
import com.example.movie_ratings.dto.review.ReviewResponse;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.Review;
import com.example.movie_ratings.entity.User;
import com.example.movie_ratings.repository.MovieRepository;
import com.example.movie_ratings.repository.ReviewRepository;
import com.example.movie_ratings.repository.UserRepository;
import com.example.movie_ratings.service.ReviewService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public List<ReviewResponse> getByMovieId(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        List<Review> reviews = reviewRepository.findByMovie(movie);

        return ValueMapper.MAPPER.convertToReviewResponseList(reviews);
    }


    @Override
    public ReviewResponse create(ReviewRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Review review = ValueMapper.MAPPER.convertToReview(request);
        review.setUser(user);
        review.setMovie(movie);

        Review saved = reviewRepository.save(review);

        return ValueMapper.MAPPER.convertToReviewResponse(saved);
    }

    @Override
    public ReviewResponse getById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return ValueMapper.MAPPER.convertToReviewResponse(review);
    }

    @Override
    public List<ReviewResponse> getAll() {
        return ValueMapper.MAPPER.convertToReviewResponseList(reviewRepository.findAll());
    }

    @Override
    public ReviewResponse update(Long id, ReviewRequest request) {

        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existing.setUser(user);
        existing.setMovie(movie);
        existing.setRating(request.getRating());
        existing.setContent(request.getContent());

        Review saved = reviewRepository.save(existing);
        return ValueMapper.MAPPER.convertToReviewResponse(saved);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewResponse> getByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Review> reviews = reviewRepository.findByUser(user);

        return ValueMapper.MAPPER.convertToReviewResponseList(reviews);
    }


    @Override
    public void updateMovieRating(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        List<Review> reviews = reviewRepository.findByMovie(movie);

        if (reviews.isEmpty()) {
            movie.setRating(0f);
            movie.setTotalRate(0);
        } else {
            float avg = (float) reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0);

            movie.setRating(avg);
            movie.setTotalRate(reviews.size());
        }

        movieRepository.save(movie);
    }
}

