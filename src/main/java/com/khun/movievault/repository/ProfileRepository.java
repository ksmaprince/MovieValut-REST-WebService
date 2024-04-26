package com.khun.movievault.repository;

import com.khun.movievault.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "DELETE FROM favourite_movies WHERE profile_id=:movieId AND movie_id=:profileId", nativeQuery = true)
    Long deleteFavouriteMovieByProfileId(Long movieId, Long profileId);
}
