package org.application.crm_DDD.features.user_context.domain.model.account;

import java.util.Objects;

public record Username(
        String username
) {
    public Username {
        Objects.requireNonNull(username, "Username must not be null");
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username must have at least 3 characters");
        }
    }

    public String value() {
        return this.username;
    }
}
