package com.khun.movievault.service;

import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.dto.profile.ProfileRequest;
import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.exception.FavouriteMovieAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    ProfileResponse getProfileById(Long profileId) throws NotFoundException;

    ProfileResponse updateProfile(ProfileRequest profileRequest, Long profileId) throws NotFoundException;

    Long saveFavoriteMovie(Long movieId, Long profileId) throws NotFoundException, FavouriteMovieAlreadyExistException;

    List<MovieResponse> getAllFavouriteMovieByProfileId(Long profileId) throws NotFoundException;
}
