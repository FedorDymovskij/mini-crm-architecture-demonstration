package org.application.crm_DDD.features.user_context.infrastructure.security.provider;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderProvider {
    private final PasswordEncoder passwordEncoder;

    public boolean matches(
            final String rawPassword,
            final String hashPassword
    ) {
        return this.passwordEncoder.matches(rawPassword, hashPassword);
    }

    public String encode(final String rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }


    public PasswordEncoderProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}
