package org.application.crm_DDD.features.user_context.application.use_case.login_user;

import java.util.UUID;

public record LoginAccountResult(
        UUID refreshToken,
        String accessToken,
        long expirationMinutes
) {
}