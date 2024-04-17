package com.khun.movievault.dto.user;

public record UserAuthRequest(
        String email,
        String password
) {
}
