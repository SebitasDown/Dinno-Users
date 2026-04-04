package com.dinno.Users.domain.model;

import java.util.UUID;

public record UpdateNotificationsCommand(
        UUID userId,
        Boolean notificationsEnabled
) {
}
