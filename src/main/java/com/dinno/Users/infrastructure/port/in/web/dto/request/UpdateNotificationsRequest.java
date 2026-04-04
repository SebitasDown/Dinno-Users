package com.dinno.Users.infrastructure.port.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNotificationsRequest {
        private Boolean notificationsEnabled;
}
