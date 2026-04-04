package com.dinno.Users.infrastructure.port.out.persistence.adapter;

import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.out.PreferencesRepository;
import com.dinno.Users.infrastructure.port.out.persistence.mapper.UserProfilePersistenceMapper;
import com.dinno.Users.infrastructure.port.out.persistence.repository.ReactiveUserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PreferencesRepositoryAdapter implements PreferencesRepository {

    private final ReactiveUserProfileRepository repository;
    private final UserProfilePersistenceMapper mapper;

    @Override
    public Mono<UserProfile> findByUserId(UUID userId) {
        return repository.findById(userId)
                .map(mapper::toModel);
    }

    @Override
    public Mono<UserProfile> save(UserProfile profile) {
        return Mono.just(profile)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toModel);
    }
}
