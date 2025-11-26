package com.example.movie_ratings.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Person not found with id: " + id);
    }
}