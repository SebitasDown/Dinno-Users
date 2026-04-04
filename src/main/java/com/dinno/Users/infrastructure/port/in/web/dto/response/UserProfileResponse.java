package com.dinno.Users.infrastructure.port.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
        private UUID userId;
        private String fullName;
        private String bio;
        private String profilePictureUrl;
        private Boolean notificationsEnabled;
        private Boolean darkMode;
        private LocalDateTime updateAt;
}
