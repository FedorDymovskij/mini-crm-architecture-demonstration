package org.application.securitydemonstration.security.model.enums;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum AccountRole implements GrantedAuthority {
    USER("ROLE_USER"),
    MODERATOR("ROLE_MODERATOR"),
    ADMIN("ROLE_ADMIN");

    private final String accountStringRole;

    AccountRole(final String accountStringRole) {
        this.accountStringRole = accountStringRole;
    }

    @Override
    public @Nullable String getAuthority() {
        return this.accountStringRole;
    }
}
