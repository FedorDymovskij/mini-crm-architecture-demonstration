package org.application.crm_DDD.features.user_context.application.use_case.refresh_session;

import java.util.UUID;

public record RefreshSessionResult(
        UUID refreshToken,
        String accessToken,
        long expirationMinutes
) {
}