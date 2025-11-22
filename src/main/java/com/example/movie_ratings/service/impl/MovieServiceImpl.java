package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.dto.movie.MovieRequest;
import com.example.movie_ratings.dto.movie.MovieResponse;
import com.example.movie_ratings.entity.Genre;
import com.example.movie_ratings.entity.Movie;
import com.example.movie_ratings.repository.GenreRepository;
import com.example.movie_ratings.repository.MovieRepository;
import com.example.movie_ratings.service.MovieService;
import com.example.movie_ratings.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;
    private final GenreRepository genreRepo;

    @Override
    public List<MovieResponse> getByGenreId(Long genreId) {

        Genre genre = genreRepo.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        List<Movie> movies = repo.findByGenreId(genreId);

        return ValueMapper.MAPPER.convertToMovieResponseList(movies);
    }


    @Override
    public MovieResponse create(MovieRequest request) {

        Genre genre = genreRepo.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Movie movie = ValueMapper.MAPPER.convertToMovie(request);
        movie.setGenre(genre);

        Movie saved = repo.save(movie);

        MovieResponse response = ValueMapper.MAPPER.convertToMovieResponse(saved);
        response.setGenreId(genre.getId());
        return response;
    }

    @Override
    public MovieResponse getById(Long id) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        MovieResponse res = ValueMapper.MAPPER.convertToMovieResponse(movie);
        res.setGenreId(movie.getGenre().getId());
        return res;
    }

    @Override
    public List<MovieResponse> getAll() {
        return ValueMapper.MAPPER.convertToMovieResponseList(repo.findAll());
    }

    @Override
    public MovieResponse update(Long id, MovieRequest request) {

        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Genre genre = genreRepo.findById(request.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setActors(request.getActors());
        movie.setGenre(genre);

        Movie saved = repo.save(movie);
        MovieResponse response = ValueMapper.MAPPER.convertToMovieResponse(saved);
        response.setGenreId(genre.getId());
        return response;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<MovieResponse> search(String keyword) {
        List<Movie> movies = repo.findByTitleContainingIgnoreCase(keyword);
        return ValueMapper.MAPPER.convertToMovieResponseList(movies);
    }
}

