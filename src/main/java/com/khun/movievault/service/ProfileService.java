package com.khun.movievault.service;

import com.khun.movievault.data.Profile;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    Profile getProfileById(Long profileId);

    Profile saveProfile(Profile profile);

    Profile updateProfile(Profile profile);
}
