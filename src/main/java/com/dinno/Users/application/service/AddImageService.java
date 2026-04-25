package com.dinno.Users.application.service;

import com.dinno.Users.domain.model.FileValidator;
import com.dinno.Users.domain.port.in.AddImageUseCase;
import com.dinno.Users.domain.port.out.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AddImageService implements AddImageUseCase {

    private final FileStorageRepository fileStorageRepository;

    @Override
    public Mono<Map<String, Object>> execute(FilePart filePart) {
        return FileValidator.validate(filePart)
                .flatMap(fileStorageRepository::upload);
    }
}
