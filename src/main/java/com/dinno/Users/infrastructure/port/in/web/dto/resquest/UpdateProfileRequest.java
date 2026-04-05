package com.dinno.Users.infrastructure.port.in.web.dto.resquest;

public record UpdateProfileRequest(
        String fullName,
        String bio,
        String profilePictureUrl
) {
}
