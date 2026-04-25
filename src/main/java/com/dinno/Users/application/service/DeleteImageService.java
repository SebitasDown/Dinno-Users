package com.dinno.Users.application.service;

import com.dinno.Users.domain.port.in.DeleteImageUseCase;
import com.dinno.Users.domain.port.out.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteImageService implements DeleteImageUseCase {

    private final FileStorageRepository fileStorageRepository;

    @Override
    public Mono<Void> execute(String publicId) {
        return fileStorageRepository.delete(publicId);
    }
}
