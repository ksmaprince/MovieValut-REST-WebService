package com.khun.movievault.service;

import com.khun.movievault.dto.movie.MovieRequest;
import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    MovieResponse saveMovie(MovieRequest movieRequest);

    List<MovieResponse> saveAllMovies(List<MovieRequest> movieRequests);

    List<MovieResponse> getAllMovies();

    MovieResponse getMovieById(Long movieId) throws NotFoundException;

}
