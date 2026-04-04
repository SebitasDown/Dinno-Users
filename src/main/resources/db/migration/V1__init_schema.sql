CREATE TABLE IF NOT EXISTS user_profile (
    user_id UUID PRIMARY KEY,
    full_name VARCHAR(255),
    bio TEXT,
    profile_picture_url VARCHAR(1024),
    notifications_enabled BOOLEAN DEFAULT TRUE,
    dark_mode BOOLEAN DEFAULT FALSE,
    update_at TIMESTAMP
);
