package com.example.movie_ratings.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {

        super("Username " + username + " already exists!");
    }
}
