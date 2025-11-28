package com.example.movie_ratings.controller;

import com.example.movie_ratings.controller.base.BaseController;
import com.example.movie_ratings.dto.APIResponse;
import com.example.movie_ratings.dto.movie.MovieRequest;
import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController extends BaseController {

    private final MovieService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<APIResponse<MovieResponse>> create(@RequestBody MovieRequest request) {
        return okResponse(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<MovieResponse>> getById(@PathVariable Long id) {
        return okResponse(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<MovieResponse>>> getAll() {
        return okResponse(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<MovieResponse>> update(@PathVariable Long id, @RequestBody MovieRequest request) {
        return okResponse(service.update(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<MovieResponse>>> search(@RequestParam String keyword) {
        return okResponse(service.search(keyword));
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<APIResponse<List<MovieResponse>>> getByGenre(
            @PathVariable Long genreId
    ) {
        return okResponse(service.getByGenreId(genreId));
    }
}

