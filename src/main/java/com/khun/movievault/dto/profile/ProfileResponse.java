package com.khun.movievault.dto.profile;

public record ProfileResponse(
    Long profileId,
    String fullName,
    String contactNo,
    String imageUrl
) {
}
