package org.application.crm_DDD.features.user_context.domain.model.session;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.Objects;
import java.util.UUID;

public record SessionId(
        UUID id
) {
    public SessionId {
        Objects.requireNonNull(id, "Session id can't be null");
    }

    public static SessionId generate() {
        return new SessionId(UuidCreator.getTimeOrderedEpoch());
    }

    public UUID value() {
        return this.id;
    }

}
