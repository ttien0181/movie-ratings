package com.example.movie_ratings.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class MovieGenreId implements Serializable {
    private Long movie;
    private Long genre;
}
