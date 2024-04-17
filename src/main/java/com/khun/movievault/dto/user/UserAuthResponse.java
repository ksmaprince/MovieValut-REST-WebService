package com.khun.movievault.dto.user;

public record UserAuthResponse(
        Long userId,
        String email,
        String jwtToken
) {
}
