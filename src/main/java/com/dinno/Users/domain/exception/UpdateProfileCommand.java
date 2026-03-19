package com.dinno.Users.domain.exception;

import java.util.UUID;

public record UpdateProfileCommand(
        UUID userId,
        String fullName,
        String bio,
        String profilePictureUrl
) {
}
