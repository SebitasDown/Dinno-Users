package com.dinno.Users.application.service;

import com.dinno.Users.domain.exception.ProfileNotFoundException;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.GetProfileUseCase;
import com.dinno.Users.domain.port.out.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class GetProfileService implements GetProfileUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetProfileService.class);
    private final UserProfileRepository repository;

    public GetProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserProfile> execute(UUID userId, String email) {
        log.info("Fetching profile for user ID: {}", userId);
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.info("Profile not found, creating default for user ID: {}", userId);
                    UserProfile profile = new UserProfile();
                    profile.setUserId(userId);
                    profile.setFullName(email);
                    return repository.save(profile);
                }))
                .doOnSuccess(profile -> log.info("Successfully fetched profile for user ID: {}", userId))
                .doOnError(e -> log.error("Error occurred while fetching profile for user ID: {}", userId, e));
    }
}
