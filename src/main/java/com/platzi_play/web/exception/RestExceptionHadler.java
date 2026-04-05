package com.platzi_play.web.exception;

import com.platzi_play.domain.exceptions.MovieAlreadyExist;
import com.platzi_play.domain.exceptions.MovieNotExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHadler {

    @ExceptionHandler(MovieAlreadyExist.class)
    public ResponseEntity<Error> handleException(Exception e) {
        Error error = new Error("MovieAlreadyExist", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MovieNotExist.class)
    public ResponseEntity<Error> handleException2(Exception e) {
        Error error = new Error("MovieNotExist", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException3(MethodArgumentNotValidException e) {
        List<Error> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add
                        (new Error(error.getField(), error.getDefaultMessage())));


        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException4(Exception e) {
        Error error = new Error("InternalServerError", e.getMessage());
        return ResponseEntity.internalServerError().body(error);

    }

}