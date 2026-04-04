package com.dinno.Users.domain.port.in;

import com.dinno.Users.domain.model.UpdateAppearanceCommnand;
import com.dinno.Users.domain.model.UserProfile;
import reactor.core.publisher.Mono;

public interface UpdateAppearanceUseCase {
    Mono<UserProfile> execute(UpdateAppearanceCommnand updateAppearance);
}
