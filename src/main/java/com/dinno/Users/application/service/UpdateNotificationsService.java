package com.dinno.Users.application.service;

import com.dinno.Users.domain.exception.ProfileNotFoundException;
import com.dinno.Users.domain.model.UpdateNotificationsCommand;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.UpdateNotificationsUseCase;
import com.dinno.Users.domain.port.out.PreferencesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UpdateNotificationsService implements UpdateNotificationsUseCase {

    private static final Logger log = LoggerFactory.getLogger(UpdateNotificationsService.class);
    private final PreferencesRepository repository;

    public UpdateNotificationsService(PreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserProfile> execute(UpdateNotificationsCommand updateNotification) {
        log.info("Updating notification settings for user ID: {}", updateNotification.userId());
        return repository.findByUserId(updateNotification.userId())
                .switchIfEmpty(Mono.error(new ProfileNotFoundException("Profile not found for userId: " + updateNotification.userId())))
                .flatMap(userProfile -> {
                    userProfile.setNotificationsEnabled(updateNotification.notificationsEnabled());
                    userProfile.setUpdateAt(LocalDateTime.now());
                    return repository.save(userProfile);
                })
                .doOnSuccess(profile -> log.info("Successfully updated notification settings for user ID: {}", updateNotification.userId()))
                .doOnError(e -> log.error("Failed to update notification settings for user ID: {}", updateNotification.userId(), e));
    }
}
