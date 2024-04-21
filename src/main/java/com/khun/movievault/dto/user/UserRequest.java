package com.khun.movievault.dto.user;

import com.khun.movievault.dto.profile.ProfileRequest;
import com.khun.movievault.model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @Valid
        @NotBlank(message = "Email cannot be blank")
        String email,

        @Valid
        @NotBlank(message = "Password cannot be blank")
        String password,
        ProfileRequest profile,

        Integer roleId
) {
}
