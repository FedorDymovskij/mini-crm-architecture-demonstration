package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.core.security.filter.authentication_filter.MainJwtAccessTokenUtils;
import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountTokenGeneratorPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.springframework.stereotype.Component;

@Component
public class LoginAccountTokenGeneratorAdapter implements LoginAccountTokenGeneratorPort {
    private final MainJwtAccessTokenUtils mainJwtAccessTokenUtils;

    @Override
    public String generateAccessToken(final Account.Snapshot snapshot) {

        return this.mainJwtAccessTokenUtils.generateAccessToken(
                snapshot.id().toString(),
                snapshot.username(),
                snapshot.role()
        );
    }

    public LoginAccountTokenGeneratorAdapter(MainJwtAccessTokenUtils mainJwtAccessTokenUtils) {
        this.mainJwtAccessTokenUtils = mainJwtAccessTokenUtils;
    }
}
