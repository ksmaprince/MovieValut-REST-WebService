package com.khun.movievault.dto.user;

public record UserAuthResponse(
        Long profileId,
        String email,
        String jwtToken
) {
}
