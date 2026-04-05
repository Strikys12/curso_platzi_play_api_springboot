package com.platzi_play.domain.exceptions;

public class MovieAlreadyExist extends RuntimeException{
    public MovieAlreadyExist(String movieTitle) {
        super("La película con el título '" + movieTitle + "' ya existe.");
    }

}
