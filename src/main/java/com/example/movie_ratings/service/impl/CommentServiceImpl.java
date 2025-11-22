package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.comment.CommentRequest;
import com.example.movie_ratings.dto.comment.CommentResponse;
import com.example.movie_ratings.entity.Comment;
import com.example.movie_ratings.entity.Review;
import com.example.movie_ratings.entity.User;
import com.example.movie_ratings.repository.CommentRepository;
import com.example.movie_ratings.repository.ReviewRepository;
import com.example.movie_ratings.repository.UserRepository;
import com.example.movie_ratings.service.CommentService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repo;
    private final UserRepository userRepo;
    private final ReviewRepository reviewRepo;

    @Override
    public CommentResponse create(CommentRequest request) {

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepo.findById(request.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Comment comment = ValueMapper.MAPPER.convertToComment(request);
        comment.setUser(user);
        comment.setReview(review);

        Comment saved = repo.save(comment);

        return ValueMapper.MAPPER.convertToCommentResponse(saved);
    }

    @Override
    public CommentResponse update(Long id, CommentRequest request) {

        Comment existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepo.findById(request.getReviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existing.setUser(user);
        existing.setReview(review);
        existing.setContent(request.getContent());

        Comment saved = repo.save(existing);

        return ValueMapper.MAPPER.convertToCommentResponse(saved);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<CommentResponse> getByReviewId(Long reviewId) {

        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        List<Comment> comments = repo.findByReview(review);

        return ValueMapper.MAPPER.convertToCommentResponseList(comments);
    }
}

