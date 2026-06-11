package org.application.crm_DDD.module.identity_context.infrastructure.security;

import org.application.crm_DDD.core.security.filter.authentication_filter.MainJwtAccessTokenUtils;
import org.application.crm_DDD.module.identity_context.application.port.TokenGeneratorPort;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.springframework.stereotype.Component;

@Component
public class TokenGeneratorAdapter implements TokenGeneratorPort {
    private final MainJwtAccessTokenUtils jwtUtils;

    @Override
    public String generateAccessToken(final Account account) {
        Account.Snapshot snapshot = account.toSnapshot();
        String userId = snapshot.id().toString();
        String username = snapshot.username();
        String role = snapshot.role();

        return this.jwtUtils.generateAccessToken(userId, username, role);
    }

    public TokenGeneratorAdapter(MainJwtAccessTokenUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
