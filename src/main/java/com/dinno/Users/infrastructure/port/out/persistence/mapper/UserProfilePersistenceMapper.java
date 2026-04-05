package com.dinno.Users.infrastructure.port.out.persistence.mapper;

import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.infrastructure.port.out.persistence.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserProfilePersistenceMapper {

    UserProfilePersistenceMapper INSTANCE = Mappers.getMapper(UserProfilePersistenceMapper.class);

    @Mapping(target = "isNewProfile", ignore = true)
    UserProfileEntity toEntity(UserProfile model);

    UserProfile toModel(UserProfileEntity entity);
}
