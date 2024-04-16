package com.khun.movievault.dto.user;

import com.khun.movievault.dto.profile.ProfileResponse;

public record UserProfileResponse(
        Long userId,
        String email,
        String password,
        ProfileResponse profile
) {
}
