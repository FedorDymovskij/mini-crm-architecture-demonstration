package org.application.crm_DDD.features.identity_context.infrastructure.web.use_case.login_account;

public record LoginAccountRequestDTO(
        String username,
        String password
) {
}
