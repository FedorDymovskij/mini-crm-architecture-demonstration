package org.application.crm_DDD.features.identity_context.domain.model.account;

import java.util.Objects;

public record Username(
        String value
) {
    public Username {
        Objects.requireNonNull(value, "Username must not be null");
        if (value.length() < 3) {
            throw new IllegalArgumentException("Username must have at least 3 characters");
        }
    }
}
