package com.platzi_play.web.controller;


import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.SuggestRequestDto;
import com.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.domain.service.MovieService;
import com.platzi_play.domain.service.PlatziPlayAiServices;
import com.platzi_play.persistance.crud.CrudMovieEntity;
import com.platzi_play.persistance.entity.MovieEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movie Controller", description = "Endpoints for managing movies")

public class MovieController {

    private final MovieService movieService;
    private final PlatziPlayAiServices aiServices;

    public MovieController(CrudMovieEntity crudMovieEntity, MovieService movieService, PlatziPlayAiServices aiServices) {
        this.movieService = movieService;

        this.aiServices = aiServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAll() {

        return ResponseEntity.ok(this.movieService.getAll());
    }



    @GetMapping("/{id}")
    @Operation(summary = "Get a movie by ID",
            description = "Returns a single movie based on the provided ID",
            responses = {
                  @ApiResponse(responseCode = "200", description = "Movie found"),
                  @ApiResponse(responseCode = "404", description = "Movie not found",
                  content = @Content(mediaType = "application/json"))
            }
    )

    public ResponseEntity<MovieDto> getById
            (@Parameter(description = "Identiticador de la película a recuperar", example = "1")
             @PathVariable long id) {
        MovieDto movieDto = this.movieService.getById(id);
        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/recommendation")
    public ResponseEntity<String> generateMovieRecommendation(@RequestBody SuggestRequestDto suggestRequestDto){
        return ResponseEntity.ok(this.aiServices.generateMovieRecommendation(suggestRequestDto.userPreference()));

    }

    @PostMapping
    public ResponseEntity add(@RequestBody MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        MovieDto movieDto = this.movieService.getById(id);
        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.movieService.delete(id));
    }
}


