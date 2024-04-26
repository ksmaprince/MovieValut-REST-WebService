package com.khun.movievault.dto.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(
        @Valid
        @NotBlank(message = "Email cannot be blank")
        String email,

        @Valid
        @NotBlank(message = "Current Password cannot be blank")
        String currentPassword,

        @Valid
        @NotBlank(message = "New Password cannot be blank")
        String newPassword

) {
}
