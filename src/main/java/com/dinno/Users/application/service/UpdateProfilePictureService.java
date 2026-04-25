package com.dinno.Users.application.service;

import com.dinno.Users.domain.exception.ResourceNotFoundException;
import com.dinno.Users.domain.model.FileValidator;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.domain.port.in.UpdateProfilePictureUseCase;
import com.dinno.Users.domain.port.out.FileStorageRepository;
import com.dinno.Users.domain.port.out.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateProfilePictureService implements UpdateProfilePictureUseCase {

    private final UserProfileRepository userProfileRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public Mono<UserProfile> execute(UUID userId, FilePart filePart) {
        return userProfileRepository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User profile not found for ID: " + userId)))
                .flatMap(profile -> FileValidator.validate(filePart)
                        .flatMap(fileStorageRepository::upload)
                        .flatMap(uploadResult -> {
                            String imageUrl = (String) uploadResult.get("secure_url");
                            profile.setProfilePictureUrl(imageUrl);
                            return userProfileRepository.save(profile);
                        })
                );
    }
}
