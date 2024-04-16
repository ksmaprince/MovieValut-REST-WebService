package com.khun.movievault.dto.profile;

public record ProfileRequest(
        String fullName,
        String contactNo,
        String imageUrl
) {
}
