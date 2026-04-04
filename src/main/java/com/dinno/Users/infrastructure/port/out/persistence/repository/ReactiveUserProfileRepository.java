package com.dinno.Users.infrastructure.port.out.persistence.repository;

import com.dinno.Users.infrastructure.port.out.persistence.entity.UserProfileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReactiveUserProfileRepository extends ReactiveCrudRepository<UserProfileEntity, UUID> {
}
