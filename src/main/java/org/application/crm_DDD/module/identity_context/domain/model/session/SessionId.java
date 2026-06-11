package org.application.crm_DDD.module.identity_context.domain.model.session;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.Objects;
import java.util.UUID;

public record SessionId(
        UUID value
) {
    public SessionId {
        Objects.requireNonNull(value, "Session value can't be null");
    }

    public static SessionId generate() {
        return new SessionId(UuidCreator.getTimeOrderedEpoch());
    }


}
