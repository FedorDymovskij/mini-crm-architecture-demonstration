package org.application.crm_DDD.module.identity_context.application.command.login_account;

public interface LoginAccountUseCase {
    LoginAccountResult execute(
            final String username,
            final String rawPassword
    );
}
