package com.khun.movievault.service.impl;

import com.khun.movievault.dto.movie.MovieRequest;
import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.model.Movie;
import com.khun.movievault.repository.MovieRepository;
import com.khun.movievault.service.MovieService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieResponse saveMovie(MovieRequest movieRequest) {
        val movie = new Movie();
        movie.setMovieTitle(movieRequest.movieTitle());
        movie.setOverview(movieRequest.overview());
        movie.setReleaseDate(movieRequest.releaseDate());
        movie.setPoster(movieRequest.poster());
        movie.setRating(movieRequest.rating());
        movie.setTrailer(movieRequest.trailer());

        val addedMovie = movieRepository.save(movie);
        return new MovieResponse(
                addedMovie.getMovieId(),
                addedMovie.getMovieTitle(),
                addedMovie.getOverview(),
                addedMovie.getReleaseDate(),
                addedMovie.getPoster(),
                addedMovie.getRating(),
                addedMovie.getTrailer()
        );
    }

    @Override
    public List<MovieResponse> saveAllMovies(List<MovieRequest> movieRequests) {
        val movies = new ArrayList<Movie>();
        movieRequests.forEach(movieRequest ->
                {
                    val movie = new Movie();
                    movie.setMovieTitle(movieRequest.movieTitle());
                    movie.setOverview(movieRequest.overview());
                    movie.setReleaseDate(movieRequest.releaseDate());
                    movie.setPoster(movieRequest.poster());
                    movie.setRating(movieRequest.rating());
                    movie.setTrailer(movieRequest.trailer());
                    movies.add(movie);

                }
        );
        val movieList = movieRepository.saveAll(movies);
        return movieList.stream().map(movie ->
                new MovieResponse(
                        movie.getMovieId(),
                        movie.getMovieTitle(),
                        movie.getOverview(),
                        movie.getReleaseDate(),
                        movie.getPoster(),
                        movie.getRating(),
                        movie.getTrailer()
                )
        ).toList();
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(movie ->
                new MovieResponse(
                        movie.getMovieId(),
                        movie.getMovieTitle(),
                        movie.getOverview(),
                        movie.getReleaseDate(),
                        movie.getPoster(),
                        movie.getRating(),
                        movie.getTrailer()
                )
        ).toList();
    }

    @Override
    public MovieResponse getMovieById(Long movieId) throws NotFoundException {
        val movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException(String.format("Movie ID %s is not found", movieId)));
        return new MovieResponse(
                movie.getMovieId(),
                movie.getMovieTitle(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getPoster(),
                movie.getRating(),
                movie.getTrailer()
        );
    }
}
