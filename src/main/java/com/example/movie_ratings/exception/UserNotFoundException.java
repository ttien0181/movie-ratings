package com.example.movie_ratings.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
    public UserNotFoundException(String userName) {
        super("User not found with username: " + userName);
    }
}