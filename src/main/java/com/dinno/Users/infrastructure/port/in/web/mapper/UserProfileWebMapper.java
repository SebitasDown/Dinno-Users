package com.dinno.Users.infrastructure.port.in.web.mapper;

import com.dinno.Users.domain.model.UpdateAppearanceCommnand;
import com.dinno.Users.domain.model.UpdateNotificationsCommand;
import com.dinno.Users.domain.model.UpdateProfileCommand;
import com.dinno.Users.domain.model.UserProfile;
import com.dinno.Users.infrastructure.port.in.web.dto.response.UserProfileResponse;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateAppearanceRequest;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateNotificationsRequest;
import com.dinno.Users.infrastructure.port.in.web.dto.request.UpdateProfileRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserProfileWebMapper {

    UserProfileWebMapper INSTANCE = Mappers.getMapper(UserProfileWebMapper.class);

    @Mapping(target = "userId", source = "userId")
    UpdateProfileCommand toCommand(UpdateProfileRequest request, UUID userId);

    @Mapping(target = "userId", source = "userId")
    UpdateAppearanceCommnand toAppearanceCommand(UpdateAppearanceRequest request, UUID userId);

    @Mapping(target = "userId", source = "userId")
    UpdateNotificationsCommand toNotificationsCommand(UpdateNotificationsRequest request, UUID userId);

    UserProfileResponse toResponse(UserProfile model);
}
