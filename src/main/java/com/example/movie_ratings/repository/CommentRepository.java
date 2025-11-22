package com.example.movie_ratings.repository;

import com.example.movie_ratings.entity.Comment;
import com.example.movie_ratings.entity.Review;
import com.example.movie_ratings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByReview(Review review);

    List<Comment> findByUser(User user);
}
