package com.khun.movievault.controller;

import com.khun.movievault.model.Movie;
import com.khun.movievault.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movievault/v1/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") Long movieId){
        return movieService.getMovieById(movieId);
    }
    @PostMapping("/addAll")
    public String saveAllMovies(@RequestBody List<Movie> movies){
        return movieService.saveAllMovies(movies);
    }

    @GetMapping("/list")
    public List<Movie> listMovies(){
        return movieService.getAllMovies();
    }

}
