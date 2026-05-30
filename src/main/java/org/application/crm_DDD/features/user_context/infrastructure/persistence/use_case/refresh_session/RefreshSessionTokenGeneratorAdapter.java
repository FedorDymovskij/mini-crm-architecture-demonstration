package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionTokenGeneratorPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.AccessTokenGeneratorProvider;

public class RefreshSessionTokenGeneratorAdapter implements RefreshSessionTokenGeneratorPort {
    private final AccessTokenGeneratorProvider accessTokenGeneratorProvider;

    @Override
    public String generateAccessToken(final Account.Snapshot snapshot) {
        return this.accessTokenGeneratorProvider.generateAccessToken(
                snapshot.id().toString(),
                snapshot.username(),
                snapshot.role()
        );
    }


    public RefreshSessionTokenGeneratorAdapter(AccessTokenGeneratorProvider accessTokenGeneratorProvider) {
        this.accessTokenGeneratorProvider = accessTokenGeneratorProvider;
    }
}
