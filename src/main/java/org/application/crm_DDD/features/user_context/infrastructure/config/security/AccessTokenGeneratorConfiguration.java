package org.application.crm_DDD.features.user_context.infrastructure.config.security;

import org.application.crm_DDD.core.security.util.JwtUtils;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.AccessTokenGeneratorProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessTokenGeneratorConfiguration {

    @Bean
    AccessTokenGeneratorProvider accessTokenGeneratorProvider(
            final @Value("${app.security.access-token.secret-key}") String secretKey,
            final @Value("${app.security.access-token.expiration-ms}") int expirationMs
    ) {
        JwtUtils jwtUtils = new JwtUtils(secretKey, expirationMs);

        return new AccessTokenGeneratorProvider(jwtUtils);
    }
}
