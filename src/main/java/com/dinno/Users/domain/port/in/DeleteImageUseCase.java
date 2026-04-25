package com.dinno.Users.domain.port.in;

import reactor.core.publisher.Mono;

public interface DeleteImageUseCase {
    Mono<Void> execute(String publicId);
}
