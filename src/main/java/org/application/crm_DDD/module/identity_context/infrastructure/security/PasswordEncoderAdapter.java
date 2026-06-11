package org.application.crm_DDD.module.identity_context.infrastructure.security;

import org.application.crm_DDD.module.identity_context.application.port.PasswordEncoderPort;
import org.application.crm_DDD.module.identity_context.domain.model.account.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(
            final Password rawPassword,
            final Password hashPassword
    ) {
        return this.passwordEncoder.matches(rawPassword.value(), hashPassword.value());
    }

    public PasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
