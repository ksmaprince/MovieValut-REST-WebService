package com.khun.movievault.dto.user;

public record UserResponse (
    Long userId,
    String email,
    String password,
    String token,
    String refreshToken
){
}
