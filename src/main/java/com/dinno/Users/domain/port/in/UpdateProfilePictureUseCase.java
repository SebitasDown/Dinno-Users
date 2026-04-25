package com.dinno.Users.domain.port.in;

import com.dinno.Users.domain.model.UserProfile;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UpdateProfilePictureUseCase {
    Mono<UserProfile> execute(UUID userId, FilePart filePart);
}
