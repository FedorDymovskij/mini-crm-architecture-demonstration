package org.application.crm_DDD.features.identity_context.application.use_case.logout_account;

import java.util.UUID;

public record LogoutAccountResult(
        UUID sessionId
) {
}
