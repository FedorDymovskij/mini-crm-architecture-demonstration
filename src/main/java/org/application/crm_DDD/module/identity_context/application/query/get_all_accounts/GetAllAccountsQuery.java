package org.application.crm_DDD.module.identity_context.application.query.get_all_accounts;

import jakarta.annotation.Nullable;

public record GetAllAccountsQuery(
        @Nullable Long page,
        @Nullable Long size
) {
}
