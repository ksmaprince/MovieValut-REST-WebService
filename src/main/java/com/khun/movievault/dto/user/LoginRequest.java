package com.khun.movievault.dto.user;

public record LoginRequest(
        String email,
        String password
) {
}
