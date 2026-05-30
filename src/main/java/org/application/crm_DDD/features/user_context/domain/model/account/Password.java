package org.application.crm_DDD.features.user_context.domain.model.account;

import java.util.Objects;

public record Password(
        String password
) {
    public Password {
        Objects.requireNonNull(password, "Password must not be null");
        if (password.length() < 4) {
            throw new IllegalArgumentException("Password length should be at least 6 characters");
        }
    }

    public String value() {
        return this.password;
    }
}
