package org.application.crm_DDD.module.identity_context.infrastructure.web.command.login_account;

public record LoginAccountRequestDTO(
        String username,
        String password
) {
}
