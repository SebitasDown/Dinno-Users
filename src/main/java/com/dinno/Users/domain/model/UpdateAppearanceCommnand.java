package com.dinno.Users.domain.model;

import java.util.UUID;

public record UpdateAppearanceCommnand(
        UUID userId,
        Boolean darkMode
) {
}
