package org.application.crm_DDD.module.identity_context.domain.model.session;

import java.time.LocalDateTime;
import java.util.Objects;

public record Expiration(
        LocalDateTime value
) {
    public Expiration {
        Objects.requireNonNull(value, "Expiration can't be null");
    }
}
