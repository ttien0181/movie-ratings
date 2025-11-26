package com.example.movie_ratings.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie_genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MovieGenreId.class)
public class MovieGenre {

    @Id
    @ManyToOne
    @JoinColumn(
            name = "movie_id",
            foreignKey = @ForeignKey(name = "fk_movie_genre_movie")
    )
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "genre_id",
            foreignKey = @ForeignKey(name = "fk_movie_genre_genre")
    )
    private Genre genre;
}
