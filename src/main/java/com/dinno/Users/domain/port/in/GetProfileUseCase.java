package com.dinno.Users.domain.port.in;

import com.dinno.Users.domain.model.UserProfile;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GetProfileUseCase {
    Mono<UserProfile> execute(UUID userId, String email);
}
