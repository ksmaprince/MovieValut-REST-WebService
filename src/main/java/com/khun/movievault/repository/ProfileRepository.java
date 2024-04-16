package com.khun.movievault.repository;

import com.khun.movievault.data.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
