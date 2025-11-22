package com.example.movie_ratings.repository;

import com.example.movie_ratings.entity.Review;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);

    List<Review> findByUser(User user);

    Optional<Review> findByUserAndMovie(User user, Movie movie);

    boolean existsByUserAndMovie(User user, Movie movie);
}
