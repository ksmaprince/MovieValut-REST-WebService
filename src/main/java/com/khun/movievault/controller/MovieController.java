package com.khun.movievault.controller;

import com.khun.movievault.dto.movie.MovieRequest;
import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movievault/v1/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest movieRequest) {
        return new ResponseEntity<>(movieService.saveMovie(movieRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable("id") Long movieId) throws NotFoundException {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<MovieResponse>> saveAllMovies(@RequestBody List<MovieRequest> movieRequests) {
        return new ResponseEntity<>(movieService.saveAllMovies(movieRequests), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieResponse>> listMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

}
