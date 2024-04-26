package com.khun.movievault.service.impl;

import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.dto.profile.ProfileRequest;
import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.exception.FavouriteMovieAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.repository.MovieRepository;
import com.khun.movievault.repository.ProfileRepository;
import com.khun.movievault.service.ProfileService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public ProfileResponse getProfileById(Long profileId) throws NotFoundException{
         val profile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException(String.format("Profile with ID: %s Not Found", profileId)));
         return new ProfileResponse(
                 profile.getProfileId(),
                 profile.getFullName(),
                 profile.getContactNo(),
                 profile.getImageUrl()
         );
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest, Long profileId) throws NotFoundException{
        val profile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException(String.format("Profile with ID: %s Not Found", profileId)));
        profile.setFullName(profileRequest.fullName());
        profile.setContactNo(profileRequest.contactNo());
        profile.setImageUrl(profileRequest.imageUrl());
        val updatedProfile = profileRepository.save(profile);
        return new ProfileResponse(
                updatedProfile.getProfileId(),
                updatedProfile.getFullName(),
                updatedProfile.getContactNo(),
                updatedProfile.getImageUrl()
        );
    }

    @Override
    public Long saveFavoriteMovie(Long profileId, Long movieId) throws NotFoundException, FavouriteMovieAlreadyExistException {
        val profile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException(String.format("Profile with ID: %s Not Found", profileId)));
        val movie = movieRepository.findById(movieId).orElseThrow(()-> new NotFoundException(String.format("Movie with ID: %s Not Found", movieId)));
        val movies = profile.getFavouriteMovies();

        if (movies.contains(movie)) {
            throw new FavouriteMovieAlreadyExistException("Favourite Movie Already Exist");
        }

        movies.add(movie);
        profile.setFavouriteMovies(movies);
        val newProfile = profileRepository.save(profile);
        return (long) newProfile.getFavouriteMovies().size();
    }

    @Override
    public List<MovieResponse> getAllFavouriteMovieByProfileId(Long profileId) throws NotFoundException {
        val profile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException(String.format("Profile with ID: %s Not Found", profileId)));
        return profile.getFavouriteMovies().stream().map(movie -> new MovieResponse(
                movie.getMovieId(),
                movie.getMovieTitle(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getPoster(),
                movie.getRating(),
                movie.getTrailer()
        )).toList();
    }
}
