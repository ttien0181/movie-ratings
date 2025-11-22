package com.example.movie_ratings.controller.base;

import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.enums.ApiStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected static final String SUCCESS = ApiStatus.SUCCESS.name();

    /**
     * Chuẩn hóa response cho các API thành công
     */
    protected <T> ResponseEntity<APIResponse<T>> okResponse(T result) {
        APIResponse<T> response = APIResponse.<T>builder()
                .status(SUCCESS)
                .result(result)
                .build();
        return ResponseEntity.ok(response);
    }
}
