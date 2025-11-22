package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.genre.GenreRequest;
import com.example.movie_ratings.dto.genre.GenreResponse;
import com.example.movie_ratings.entity.Genre;
import com.example.movie_ratings.repository.GenreRepository;
import com.example.movie_ratings.service.GenreService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repo;

    @Override
    public GenreResponse create(GenreRequest request) {
        Genre genre = ValueMapper.MAPPER.convertToGenre(request);
        Genre saved = repo.save(genre);
        return ValueMapper.MAPPER.convertToGenreResponse(saved);
    }

    @Override
    public GenreResponse getById(Long id) {
        Genre genre = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        return ValueMapper.MAPPER.convertToGenreResponse(genre);
    }

    @Override
    public List<GenreResponse> getAll() {
        return ValueMapper.MAPPER.convertToGenreResponseList(repo.findAll());
    }

    @Override
    public GenreResponse update(Long id, GenreRequest request) {
        Genre genre = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        genre.setName(request.getName());
        genre.setDescription(request.getDescription());

        Genre saved = repo.save(genre);

        return ValueMapper.MAPPER.convertToGenreResponse(saved);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
