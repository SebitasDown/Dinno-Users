package com.dinno.Users.domain.port.out;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface FileStorageRepository {
    Mono<Map<String, Object>> upload(FilePart filePart);
    Mono<Void> delete(String publicId);
}
