package com.example.movie_ratings.dto.auth;

import lombok.Data;

@Data
public class SendVerificationCodeRequest {
    private String email;
}