package com.platzi_play.domain.dto;

import com.platzi_play.domain.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jdk.jfr.MemoryAddress;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message = "El titulo es obligatorio")
        String title,

        @PastOrPresent(message = "La fecha de lanzamiento no puede ser futura")
        LocalDate releaseDate,

        @Min(value = 0, message = "No puede ser menor que 0")
        @Max(value = 5, message = "No puede ser mayor que 5")
        Double rating
) {
}
