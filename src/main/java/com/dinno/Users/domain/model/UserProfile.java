package com.dinno.Users.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserProfile {
    private UUID userId;
    private String fullName;
    private String bio;
    private String profilePictureUrl;
    private Boolean notificationsEnabled = true;
    private Boolean darkMode = false;
    private LocalDateTime updateAt;

    public UserProfile() {
    }

    public UserProfile(UUID userId, String fullName, String bio, String profilePictureUrl, Boolean notificationsEnabled, Boolean darkMode, LocalDateTime updateAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.notificationsEnabled = notificationsEnabled;
        this.darkMode = darkMode;
        this.updateAt = updateAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
