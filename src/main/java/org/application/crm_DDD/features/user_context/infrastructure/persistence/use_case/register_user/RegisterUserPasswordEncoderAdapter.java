package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.register_user;

import org.application.crm_DDD.features.user_context.application.use_case.register_user.RegisterUserPasswordEncoderPort;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.PasswordEncoderProvider;

public class RegisterUserPasswordEncoderAdapter implements RegisterUserPasswordEncoderPort {
    private final PasswordEncoderProvider passwordEncoderProvider;

    @Override
    public String encode(String rawPassword) {
        return this.passwordEncoderProvider.encode(rawPassword);
    }

    public RegisterUserPasswordEncoderAdapter(PasswordEncoderProvider passwordEncoderProvider) {
        this.passwordEncoderProvider = passwordEncoderProvider;
    }
}
