package com.titan.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum UserRole {
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    @Getter
    private final String role;

    public SimpleGrantedAuthority getAuthorities() {
        //var authorities = getPermission();
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}
