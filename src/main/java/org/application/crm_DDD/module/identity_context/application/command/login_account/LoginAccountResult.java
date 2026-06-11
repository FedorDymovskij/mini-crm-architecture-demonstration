package org.application.crm_DDD.module.identity_context.application.command.login_account;

import java.util.UUID;

public record LoginAccountResult(
        UUID refreshToken,
        String accessToken
) {
}