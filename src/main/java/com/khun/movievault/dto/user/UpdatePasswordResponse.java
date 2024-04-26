package com.khun.movievault.dto.user;

public record UpdatePasswordResponse(
        String email,
        String message
) {
}
