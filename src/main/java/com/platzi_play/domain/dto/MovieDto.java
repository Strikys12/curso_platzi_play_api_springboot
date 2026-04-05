package com.platzi_play.domain.dto;

import com.platzi_play.domain.Genre;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        Boolean state
) {
}
