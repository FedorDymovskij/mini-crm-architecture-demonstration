package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.register_account;

import org.application.crm_DDD.features.identity_context.application.use_case.register_account.RegisterAccountPasswordEncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterAccountPasswordEncoderAdapter implements RegisterAccountPasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(final String rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    public RegisterAccountPasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
