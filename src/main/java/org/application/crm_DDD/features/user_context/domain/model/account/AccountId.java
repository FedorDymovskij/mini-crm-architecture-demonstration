package org.application.crm_DDD.features.user_context.domain.model.account;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.Objects;
import java.util.UUID;

public record AccountId(
        UUID id
) {
    public AccountId {
        Objects.requireNonNull(id, "id must not be null");
    }

    public UUID value() {
        return this.id;
    }

    public static AccountId generate() {
        return new AccountId(UuidCreator.getTimeOrderedEpoch());
    }
}
