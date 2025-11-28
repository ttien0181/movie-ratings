package com.example.movie_ratings.dto.external;

import com.example.movie_ratings.entity.Genre;
import lombok.Data;

import java.util.List;

// dung de parse tu json
@Data
public class ExternalMovieDTO {

    private String id;
    private String primaryTitle; // title
    private Integer startYear; // ReleaseYear
    private String plot; // des

    private PrimaryImage primaryImage;
    private List<String> genres;

    @Data
    public static class PrimaryImage {
        private String url;  // PosterUrl
    }

}

