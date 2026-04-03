package com.dinno.Users.domain.port.in;

import com.dinno.Users.domain.model.UpdateNotificationsCommand;
import com.dinno.Users.domain.model.UpdateProfileCommand;
import com.dinno.Users.domain.model.UserProfile;
import reactor.core.publisher.Mono;

public interface UpdateNotificationsUseCase {
    Mono<UserProfile> execute(UpdateNotificationsCommand updateNotification);
}
