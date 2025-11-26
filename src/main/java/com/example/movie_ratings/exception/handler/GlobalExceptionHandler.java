package com.example.movie_ratings.exception.handler;

import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.common.ErrorDetail;
import com.example.movie_ratings.exception.UserNotFoundException;
import com.example.movie_ratings.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String FAILURE = "FAILURE";

    private ResponseEntity<APIResponse<ErrorDetail>> buildError(String field, String message, HttpStatus status) {
        ErrorDetail error = ErrorDetail.builder()
                .field(field)
                .errorMessage(message)
                .timestamp(LocalDateTime.now())
                .build();
        APIResponse<ErrorDetail> body = APIResponse.<ErrorDetail>builder()
                .status(FAILURE)
                .errors(Collections.singletonList(error))
                .build();
        return ResponseEntity.status(status).body(body);
    }

    // ⚙️ User-related
    @ExceptionHandler({
            UserNotFoundException.class,
            UsernameAlreadyExistsException.class
    })
    public ResponseEntity<APIResponse<ErrorDetail>> handleUserExceptions(RuntimeException ex) {
        return buildError("user", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // ⚙️ Spring Security login errors
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse<ErrorDetail>> handleBadCredentials(BadCredentialsException ex) {
        return buildError("password", "Sai tên đăng nhập hoặc mật khẩu", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<APIResponse<ErrorDetail>> handleDisabledAccount(DisabledException ex) {
        return buildError("account", "Tài khoản đã bị vô hiệu hóa", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<APIResponse<ErrorDetail>> handleUsernameNotFound(UsernameNotFoundException ex) {
        return buildError("username", "Không tìm thấy người dùng", HttpStatus.NOT_FOUND);
    }

    // ⚙️ Bắt tất cả lỗi không xác định khác
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<ErrorDetail>> handleAll(Exception ex) {
        ex.printStackTrace();
        return buildError("server", "Đã xảy ra lỗi: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
