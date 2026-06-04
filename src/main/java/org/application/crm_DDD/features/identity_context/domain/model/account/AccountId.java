package org.application.crm_DDD.features.identity_context.domain.model.account;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.Objects;
import java.util.UUID;

public record AccountId(
        UUID value
) {
    public AccountId {
        Objects.requireNonNull(value, "value must not be null");
    }

    public static AccountId generate() {
        return new AccountId(UuidCreator.getTimeOrderedEpoch());
    }
}
