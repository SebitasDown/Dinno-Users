package com.dinno.Users.domain.model;


import java.util.UUID;

public record UpdateProfileCommand(
        UUID userId,
        String fullName,
        String bio,
        String profilePictureUrl
) {
}
