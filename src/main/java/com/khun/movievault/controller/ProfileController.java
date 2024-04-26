package com.khun.movievault.controller;

import com.khun.movievault.dto.movie.MovieResponse;
import com.khun.movievault.dto.profile.ProfileRequest;
import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.exception.FavouriteMovieAlreadyExistException;
import com.khun.movievault.exception.NotFoundException;
import com.khun.movievault.service.ProfileService;
import com.khun.movievault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movievault/v1/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable Long profileId) throws NotFoundException {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody ProfileRequest profileRequest, @PathVariable Long profileId) throws NotFoundException {
        return ResponseEntity.ok(profileService.updateProfile(profileRequest, profileId));
    }

    @PatchMapping("/addFavourite")
    public ResponseEntity<Long> addFavouriteMovie(@Param("profileId") Long profileId, @Param("movieId") Long movieId) throws NotFoundException, FavouriteMovieAlreadyExistException {
        return new ResponseEntity<>(profileService.saveFavoriteMovie(profileId, movieId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/favourites/{profileId}")
    public ResponseEntity<List<MovieResponse>> getAllFavouriteMovie(@PathVariable Long profileId) throws NotFoundException {
        return ResponseEntity.ok(profileService.getAllFavouriteMovieByProfileId(profileId));
    }

}
