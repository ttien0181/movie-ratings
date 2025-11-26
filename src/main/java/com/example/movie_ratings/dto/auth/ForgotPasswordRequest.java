package com.example.movie_ratings.dto.auth;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String email;
}