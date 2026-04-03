package com.dinno.Users.application.service;

import com.dinno.Users.domain.model.UpdateNotificationsCommand;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.UpdateNotificationsUseCase;
import com.dinno.Users.domain.port.out.PreferencesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UpdateNotificationsService implements UpdateNotificationsUseCase {

    private final PreferencesRepository repository;

    public UpdateNotificationsService(PreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserProfile> execute(UpdateNotificationsCommand updateNotification) {
        return repository.findByUserId(updateNotification.userId())
                .defaultIfEmpty(defaultProfile(updateNotification.userId()))
                .flatMap(userProfile -> {
                    userProfile.setNotificationsEnabled(updateNotification.notificationsEnabled());
                    userProfile.setUpdateAt(LocalDateTime.now());
                    return repository.save(userProfile);
                });
    }

    private UserProfile defaultProfile(UUID userId) {
        return new UserProfile(
                userId,
                null,
                null,
                null,
                true,
                true,
                LocalDateTime.now()
        );
    }
}
