package com.dinno.Users.domain.port.out;

import com.dinno.Users.domain.model.UserProfile;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PreferencesRepository {
    Mono<UserProfile> findByUserId(UUID userId);
    Mono<UserProfile> save(UserProfile profile);
}
