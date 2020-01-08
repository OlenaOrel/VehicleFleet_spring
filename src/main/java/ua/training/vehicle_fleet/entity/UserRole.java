package ua.training.vehicle_fleet.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_DRIVER,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
