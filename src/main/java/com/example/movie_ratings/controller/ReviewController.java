package com.example.movie_ratings.controller;

import com.example.movie_ratings.controller.base.BaseController;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.review.ReviewRequest;
import com.example.movie_ratings.dto.review.ReviewResponse;
import com.example.movie_ratings.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController extends BaseController {

    private final ReviewService reviewService;

    @GetMapping
    ResponseEntity<APIResponse<List<ReviewResponse>>> getAll() {
        return okResponse(reviewService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ReviewResponse>> getOne(@PathVariable Long id) {
        return okResponse(reviewService.getById(id));
    }

    @PostMapping
    ResponseEntity<APIResponse<ReviewResponse>> create(@RequestBody ReviewRequest request) {

        ReviewResponse response = reviewService.create(request);

        // Cập nhật rating phim
        reviewService.calcAddMovieRating(request.getMovieId(), request.getRating());

        return okResponse(response);
    }

    // thay đổi rating (id là id của review)
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<ReviewResponse>> update(
            @PathVariable Long id,
            @RequestBody ReviewRequest request
    ) {
        ReviewResponse response = reviewService.update(id, request);

        reviewService.calcUpdateMovieRating(id, request.getRating(), request.getMovieId());

        return okResponse(response);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.calcRemoveMovieRating(id);
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<APIResponse<List<ReviewResponse>>> getByMovie(@PathVariable Long movieId) {
        return okResponse(reviewService.getByMovieId(movieId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse<List<ReviewResponse>>> getByUser(@PathVariable Long userId) {
        return okResponse(reviewService.getByUserId(userId));
    }


}

