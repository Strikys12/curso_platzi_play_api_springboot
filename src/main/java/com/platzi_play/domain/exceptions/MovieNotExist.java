package com.platzi_play.domain.exceptions;

public class MovieNotExist extends RuntimeException{

    public MovieNotExist(String movieTitle) {
        super("La película con el título '" + movieTitle + "' no existe.");
    }
}
