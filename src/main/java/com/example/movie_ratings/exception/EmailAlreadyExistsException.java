package com.example.movie_ratings.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String username) {

        super("Email " + username + " already exists!");
    }
}
