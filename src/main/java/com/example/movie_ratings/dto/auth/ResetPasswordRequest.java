package com.example.movie_ratings.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String verificationCode;
    private String newPassword;
}