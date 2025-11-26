package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.movie.MovieRequest;
import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Genre;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.entity.MovieGenre;
import com.example.movie_ratings.repository.GenreRepository;
import com.example.movie_ratings.repository.MovieGenreRepository;
import com.example.movie_ratings.repository.MovieRepository;
import com.example.movie_ratings.service.MovieService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;
    private final GenreRepository genreRepo;
    private final MovieGenreRepository movieGenreRepo;
    private final ValueMapper mapper;

    @Override
    public List<MovieResponse> getByGenreId(Long genreId) {
        List<Movie> movies = repo.findByGenreId(genreId);
        return mapper.convertToMovieResponseList(movies);
    }

    @Override
    @Transactional
    public MovieResponse create(MovieRequest request) {

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setActors(request.getActors());
        movie.setPosterUrl(request.getPosterUrl());

        Movie saved = repo.save(movie);

        // Lưu bảng trung gian
        for (Long gid : request.getGenreIds()) {
            Genre genre = genreRepo.findById(gid)
                    .orElseThrow(() -> new RuntimeException("Genre not found: " + gid));

            movieGenreRepo.save(new MovieGenre(saved, genre));
        }

        saved = repo.findById(saved.getId()).orElseThrow();
        return mapper.convertToMovieResponse(saved);
    }

    @Override
    public MovieResponse getById(Long id) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return mapper.convertToMovieResponse(movie);
    }

    @Override
    public List<MovieResponse> getAll() {
        return mapper.convertToMovieResponseList(repo.findAll());
    }

    @Override
    @Transactional
    public MovieResponse update(Long id, MovieRequest request) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setActors(request.getActors());
        movie.setPosterUrl(request.getPosterUrl());

        // Xoá genre cũ
        movieGenreRepo.deleteByMovie(movie);

        // Thêm genre mới
        for (Long gid : request.getGenreIds()) {
            Genre genre = genreRepo.findById(gid)
                    .orElseThrow(() -> new RuntimeException("Genre not found: " + gid));
            movieGenreRepo.save(new MovieGenre(movie, genre));
        }

        return mapper.convertToMovieResponse(movie);
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<MovieResponse> search(String keyword) {
        List<Movie> movies = repo.findByTitleContainingIgnoreCase(keyword);
        return mapper.convertToMovieResponseList(movies);
    }
}
