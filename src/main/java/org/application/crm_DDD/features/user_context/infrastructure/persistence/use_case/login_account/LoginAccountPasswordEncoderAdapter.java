package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountPasswordEncoderPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Password;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.PasswordEncoderProvider;

public class LoginAccountPasswordEncoderAdapter implements LoginAccountPasswordEncoderPort {
    private final PasswordEncoderProvider passwordEncoderProvider;

    @Override
    public boolean matches(
            final Password rawPassword,
            final Password hashPassword
    ) {
        return this.passwordEncoderProvider.matches(rawPassword.value(), hashPassword.value());
    }

    public LoginAccountPasswordEncoderAdapter(PasswordEncoderProvider passwordEncoderProvider) {
        this.passwordEncoderProvider = passwordEncoderProvider;
    }
}
