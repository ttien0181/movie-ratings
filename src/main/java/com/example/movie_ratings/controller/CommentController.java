package com.example.movie_ratings.controller;

import com.example.movie_ratings.controller.base.BaseController;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.comment.CommentRequest;
import com.example.movie_ratings.dto.comment.CommentResponse;
import com.example.movie_ratings.entity.Comment;
import com.example.movie_ratings.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<APIResponse<CommentResponse>> create(@RequestBody CommentRequest request) {
        return okResponse(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CommentResponse>> update(
            @PathVariable Long id,
            @RequestBody CommentRequest request
    ) {
        return okResponse(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<APIResponse<List<CommentResponse>>> getByReview(@PathVariable Long reviewId) {
        return okResponse(service.getByReviewId(reviewId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<APIResponse<List<CommentResponse>>> getByUser(@PathVariable Long userId) {
        return okResponse(service.getByUserId(userId));
    }
}

