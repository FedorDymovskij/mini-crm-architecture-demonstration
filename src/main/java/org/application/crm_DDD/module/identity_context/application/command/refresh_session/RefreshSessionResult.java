package org.application.crm_DDD.module.identity_context.application.command.refresh_session;

import java.util.UUID;

public record RefreshSessionResult(
        UUID refreshToken,
        String accessToken
) {
}