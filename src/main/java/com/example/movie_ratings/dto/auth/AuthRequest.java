package com.example.movie_ratings.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}