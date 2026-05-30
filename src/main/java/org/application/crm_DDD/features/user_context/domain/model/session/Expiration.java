package org.application.crm_DDD.features.user_context.domain.model.session;

import java.time.LocalDateTime;
import java.util.Objects;

public record Expiration(
        LocalDateTime expiration
) {
    public Expiration {
        Objects.requireNonNull(expiration, "Expiration can't be null");
    }

    public LocalDateTime value() {
        return this.expiration;
    }
}
