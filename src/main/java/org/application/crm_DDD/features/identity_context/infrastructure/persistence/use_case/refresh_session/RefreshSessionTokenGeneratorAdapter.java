package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.core.security.filter.authentication_filter.MainJwtAccessTokenUtils;
import org.application.crm_DDD.features.identity_context.application.use_case.refresh_session.RefreshSessionTokenGeneratorPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.springframework.stereotype.Component;

@Component
public class RefreshSessionTokenGeneratorAdapter implements RefreshSessionTokenGeneratorPort {
    private final MainJwtAccessTokenUtils mainJwtAccessTokenUtils;

    @Override
    public String generateAccessToken(final Account.Snapshot snapshot) {
        return this.mainJwtAccessTokenUtils.generateAccessToken(
                snapshot.id().toString(),
                snapshot.username(),
                snapshot.role()
        );
    }


    public RefreshSessionTokenGeneratorAdapter(MainJwtAccessTokenUtils mainJwtAccessTokenUtils) {
        this.mainJwtAccessTokenUtils = mainJwtAccessTokenUtils;
    }
}
