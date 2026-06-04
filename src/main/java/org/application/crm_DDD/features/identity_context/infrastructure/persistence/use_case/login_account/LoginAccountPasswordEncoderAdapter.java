package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountPasswordEncoderPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAccountPasswordEncoderAdapter implements LoginAccountPasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean matches(
            final Password rawPassword,
            final Password hashPassword
    ) {
        return this.passwordEncoder.matches(rawPassword.value(), hashPassword.value());
    }

    public LoginAccountPasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
