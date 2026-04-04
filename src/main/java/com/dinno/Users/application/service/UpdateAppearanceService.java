package com.dinno.Users.application.service;

import com.dinno.Users.domain.exception.ProfileNotFoundException;
import com.dinno.Users.domain.model.UpdateAppearanceCommnand;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.UpdateAppearanceUseCase;
import com.dinno.Users.domain.port.out.PreferencesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UpdateAppearanceService implements UpdateAppearanceUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateAppearanceService.class);
    private final PreferencesRepository repository;

    public UpdateAppearanceService(PreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserProfile> execute(UpdateAppearanceCommnand updateAppearance) {
        log.info("Updating appearance settings for user ID: {}", updateAppearance.userId());
        return repository.findByUserId(updateAppearance.userId())
                .switchIfEmpty(Mono.error(new ProfileNotFoundException("Profile not found for userId: " + updateAppearance.userId())))
                .flatMap(userProfile -> {
                    userProfile.setDarkMode(updateAppearance.darkMode());
                    userProfile.setUpdateAt(LocalDateTime.now());
                    return repository.save(userProfile);
                })
                .doOnSuccess(profile -> log.info("Successfully updated appearance settings for user ID: {}", updateAppearance.userId()))
                .doOnError(e -> log.error("Failed to update appearance settings for user ID: {}", updateAppearance.userId(), e));
    }
}
