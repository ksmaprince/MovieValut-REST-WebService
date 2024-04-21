package com.khun.movievault.dto.user;

import com.khun.movievault.dto.profile.ProfileResponse;
import com.khun.movievault.dto.role.RoleResponse;

public record UserProfileResponse(
        Long userId,
        String email,
        String password,
        ProfileResponse profile,
        RoleResponse role
) {
}
