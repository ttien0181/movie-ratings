package com.example.movie_ratings.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(
            name = "genre_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_movies_genre")
    )
    private Genre genre;

    @Column
    private Float rating; // trung bình từ reviews

    @Column(name = "total_rate")
    private Integer totalRate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String actors;
}
