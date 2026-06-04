package org.application.crm_DDD.features.identity_context.application.query.get_account_by_id;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record GetAccountByIdQuery(
        @Nullable UUID accountId
) {
}
