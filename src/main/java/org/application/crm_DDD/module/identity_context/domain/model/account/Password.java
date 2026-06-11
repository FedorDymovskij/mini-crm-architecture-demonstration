package org.application.crm_DDD.module.identity_context.domain.model.account;

import java.util.Objects;

public record Password(
        String value
) {
    public Password {
        Objects.requireNonNull(value, "Password must not be null");
        if (value.length() < 4) {
            throw new IllegalArgumentException("Password length should be at least 6 characters");
        }
    }

}
