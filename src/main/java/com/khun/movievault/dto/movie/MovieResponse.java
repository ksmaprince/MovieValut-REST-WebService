package com.khun.movievault.dto.movie;

import java.time.LocalDate;

public record MovieResponse(
        Long movieId,
        String movieTitle,
        String overview,
        LocalDate releaseDate,
        String poster,
        Double rating,
        String trailer
) {
}
