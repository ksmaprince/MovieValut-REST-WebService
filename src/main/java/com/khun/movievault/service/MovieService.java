package com.khun.movievault.service;

import com.khun.movievault.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    Movie saveMovie(Movie movie);

    String saveAllMovies(List<Movie> movies);

    List<Movie> getAllMovies();

    Movie getMovieById(Long movieId);

}
