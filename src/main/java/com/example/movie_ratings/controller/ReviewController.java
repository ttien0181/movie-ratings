package com.example.movie_ratings.controller;

import com.example.movie_ratings.controller.base.BaseController;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.review.ReviewRequest;
import com.example.movie_ratings.dto.review.ReviewResponse;
import com.example.movie_ratings.entity.Review;
import com.example.movie_ratings.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController extends BaseController {

    private final ReviewService service;

    @GetMapping
    ResponseEntity<APIResponse<List<ReviewResponse>>> getAll() {
        return okResponse(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ReviewResponse>> getOne(@PathVariable Long id) {
        return okResponse(service.getById(id));
    }

    @PostMapping
    ResponseEntity<APIResponse<ReviewResponse>> create(@RequestBody ReviewRequest request) {

        ReviewResponse response = service.create(request);

        // Cập nhật rating phim
        service.updateMovieRating(request.getMovieId());

        return okResponse(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<APIResponse<ReviewResponse>> update(
            @PathVariable Long id,
            @RequestBody ReviewRequest request
    ) {
        ReviewResponse response = service.update(id, request);
        service.updateMovieRating(request.getMovieId());
        return okResponse(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        ReviewResponse old = service.getById(id);
        service.delete(id);
        service.updateMovieRating(old.getMovieId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<APIResponse<List<ReviewResponse>>> getByMovie(@PathVariable Long movieId) {
        return okResponse(service.getByMovieId(movieId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse<List<ReviewResponse>>> getByUser(@PathVariable Long userId) {
        return okResponse(service.getByUserId(userId));
    }


}

