package com.khun.movievault.controller;

import com.khun.movievault.data.Movie;
import com.khun.movievault.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movievault/v1/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("movie")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("movie/{id}")
    public Movie getMovie(@PathVariable("id") Long movieId){
        return movieService.getMovieById(movieId);
    }
    @PostMapping("movies")
    public String saveAllMovies(@RequestBody List<Movie> movies){
        return movieService.saveAllMovies(movies);
    }

    @GetMapping("movies")
    public List<Movie> listMovies(){
        return movieService.getAllMovies();
    }

}
