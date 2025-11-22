package com.example.movie_ratings.controller;

import com.example.movie_ratings.controller.base.BaseController;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.genre.GenreRequest;
import com.example.movie_ratings.dto.genre.GenreResponse;
import com.example.movie_ratings.entity.Genre;
import com.example.movie_ratings.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController extends BaseController {

    private final GenreService service;

    @PostMapping
    public ResponseEntity<APIResponse<GenreResponse>> create(@RequestBody GenreRequest request) {
        return okResponse(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GenreResponse>> getById(@PathVariable Long id) {
        return okResponse(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<GenreResponse>>> getAll() {
        return okResponse(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<GenreResponse>> update(
            @PathVariable Long id,
            @RequestBody GenreRequest request
    ) {
        return okResponse(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

