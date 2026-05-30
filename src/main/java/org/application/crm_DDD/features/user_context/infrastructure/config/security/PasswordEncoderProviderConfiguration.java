package org.application.crm_DDD.features.user_context.infrastructure.config.security;

import org.application.crm_DDD.features.user_context.infrastructure.security.provider.PasswordEncoderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderProviderConfiguration {
    @Bean
    public PasswordEncoderProvider passwordEncoderProvider(
            final PasswordEncoder passwordEncoder
    ) {
        return new PasswordEncoderProvider(passwordEncoder);
    }
}
