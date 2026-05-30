package org.application.crm_DDD.features.user_context.infrastructure.security.provider;

import org.application.crm_DDD.core.security.util.JwtUtils;

public class AccessTokenGeneratorProvider {

    private final JwtUtils jwtUtils;

    public String generateAccessToken(
            final String accountId,
            final String username,
            final String role
    ) {

        return this.jwtUtils.generateJwtToken(
                accountId,
                username,
                role);
    }

    public AccessTokenGeneratorProvider(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
