package com.khun.movievault.service.impl;

import com.khun.movievault.model.Movie;
import com.khun.movievault.repository.MovieRepository;
import com.khun.movievault.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public String saveAllMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
        return "All model are saved";
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElse(null);
    }
}
