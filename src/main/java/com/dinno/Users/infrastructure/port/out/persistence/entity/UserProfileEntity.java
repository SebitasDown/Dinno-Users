package com.dinno.Users.infrastructure.port.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("user_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileEntity implements Persistable<UUID> {

    @Id
    @Column("user_id")
    private UUID userId;

    @Column("full_name")
    private String fullName;

    @Column("bio")
    private String bio;

    @Column("profile_picture_url")
    private String profilePictureUrl;

    @Column("notifications_enabled")
    @Builder.Default
    private Boolean notificationsEnabled = true;

    @Column("dark_mode")
    @Builder.Default
    private Boolean darkMode = false;

    @Column("update_at")
    private LocalDateTime updateAt;

    @Transient
    private boolean isNewProfile;

    @Override
    public UUID getId() {
        return userId;
    }

    @Override
    public boolean isNew() {
        return isNewProfile || userId == null;
    }
}