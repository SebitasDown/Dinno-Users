package com.dinno.Users.domain.port.in;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AddImageUseCase {
    Mono<Map<String, Object>> execute(FilePart filePart);
}
