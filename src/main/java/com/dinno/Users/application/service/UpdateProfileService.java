package com.dinno.Users.application.service;

import com.dinno.Users.domain.model.UpdateProfileCommand;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.UpdateProfileUseCase;
import com.dinno.Users.domain.port.out.UserProfileRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateProfileService implements UpdateProfileUseCase {

    private final UserProfileRepository repository;

    public UpdateProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserProfile> execute(UpdateProfileCommand command) {
        return repository.findByUserId(command.userId())
            .switchIfEmpty(Mono.error(new com.dinno.Users.domain.exception.ProfileNotFoundException("Profile not found for userId: " + command.userId())))
            .flatMap(profile -> {
                profile.setFullName(command.fullName());
                profile.setBio(command.bio());
                profile.setProfilePictureUrl(command.profilePictureUrl());
                profile.setUpdateAt(java.time.LocalDateTime.now());
                
                return repository.save(profile);
            });
    }
}
