package org.application.crm_DDD.module.identity_context.application.command.logout_account;

import java.util.UUID;

public record LogoutAccountResult(
        UUID sessionId
) {
}
