package com.platzi_play.persistance.crud;


import com.platzi_play.persistance.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMovieEntity extends CrudRepository<MovieEntity, Long> {

    MovieEntity findFirstByTitulo(String titulo);
}
