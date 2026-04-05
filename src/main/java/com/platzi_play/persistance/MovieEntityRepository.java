package com.platzi_play.persistance;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.domain.exceptions.MovieAlreadyExist;
import com.platzi_play.domain.exceptions.MovieNotExist;
import com.platzi_play.domain.repository.MovieRepository;
import com.platzi_play.persistance.crud.CrudMovieEntity;
import com.platzi_play.persistance.entity.MovieEntity;
import com.platzi_play.persistance.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MovieEntityRepository implements MovieRepository   {

    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto((Iterable<MovieEntity>) this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
     }

    @Override
    public MovieDto add(MovieDto movieDto) {
     if(this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null){
         throw new MovieAlreadyExist(movieDto.title());
      }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if(movieEntity == null){
            throw new MovieNotExist(updateMovieDto.title());
        }


        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);

        return this.movieMapper.toDto(crudMovieEntity.save(movieEntity));

    }

    @Override
    public MovieDto delete(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity == null) return  null;

        this.crudMovieEntity.delete(movieEntity);

        return this.movieMapper.toDto(movieEntity);
    }
}
