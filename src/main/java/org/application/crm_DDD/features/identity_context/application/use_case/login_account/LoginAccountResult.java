package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

import java.util.UUID;

public record LoginAccountResult(
        UUID refreshToken,
        String accessToken,
        long expirationMinutes
) {
}