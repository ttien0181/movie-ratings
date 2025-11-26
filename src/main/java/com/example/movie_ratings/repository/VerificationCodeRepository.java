package com.example.movie_ratings.repository;

import com.example.movie_ratings.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByEmailAndCodeAndType(
            String email,
            String code,
            VerificationCode.CodeType type
    );

    void deleteByEmail(String email);
}