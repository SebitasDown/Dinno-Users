package com.dinno.Users.infrastructure.port.in.web.controller;

import com.dinno.Users.domain.port.in.GetProfileUseCase;
import com.dinno.Users.domain.port.in.UpdateAppearanceUseCase;
import com.dinno.Users.domain.port.in.UpdateNotificationsUseCase;
import com.dinno.Users.domain.port.in.UpdateProfileUseCase;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateAppearanceRequest;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateNotificationsRequest;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateProfileRequest;
import com.dinno.Users.infrastructure.port.in.web.dto.response.UserProfileResponse;
import com.dinno.Users.infrastructure.port.in.web.mapper.UserProfileWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/profile")
@RequiredArgsConstructor
public class UserProfileController {

        private final GetProfileUseCase getProfileUseCase;
        private final UpdateProfileUseCase updateProfileUseCase;
        private final UpdateAppearanceUseCase updateAppearanceUseCase;
        private final UpdateNotificationsUseCase updateNotificationsUseCase;
        private final UserProfileWebMapper mapper;

        @GetMapping
        public Mono<ResponseEntity<UserProfileResponse>> getProfile(
                        @RequestHeader("X-User-Id") UUID userId,
                        @RequestHeader(value = "X-User-Email", required = false) String email) {
                return getProfileUseCase.execute(userId, email)
                                .map(mapper::toResponse)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @PutMapping
        public Mono<ResponseEntity<UserProfileResponse>> updateProfile(
                        @RequestHeader("X-User-Id") UUID userId,
                        @RequestBody UpdateProfileRequest request) {
                return updateProfileUseCase.execute(mapper.toCommand(request, userId))
                                .map(mapper::toResponse)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @PatchMapping("/appearance")
        public Mono<ResponseEntity<Void>> updateAppearance(
                        @RequestHeader("X-User-Id") UUID userId,
                        @RequestBody UpdateAppearanceRequest request) {
                return updateAppearanceUseCase.execute(mapper.toAppearanceCommand(request, userId))
                                .then(Mono.just(ResponseEntity.noContent().build()));
        }

        @PatchMapping("/notifications")
        public Mono<ResponseEntity<Void>> updateNotifications(
                        @RequestHeader("X-User-Id") UUID userId,
                        @RequestBody UpdateNotificationsRequest request) {
                return updateNotificationsUseCase.execute(mapper.toNotificationsCommand(request, userId))
                                .then(Mono.just(ResponseEntity.noContent().build()));
        }
}
