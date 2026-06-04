package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

public interface LoginAccountUseCase {
    LoginAccountResult execute(
            final String username,
            final String rawPassword
    );
}
