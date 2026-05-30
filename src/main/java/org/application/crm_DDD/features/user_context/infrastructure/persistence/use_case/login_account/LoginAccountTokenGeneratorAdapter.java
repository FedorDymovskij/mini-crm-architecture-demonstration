package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountTokenGeneratorPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.infrastructure.security.provider.AccessTokenGeneratorProvider;

public class LoginAccountTokenGeneratorAdapter implements LoginAccountTokenGeneratorPort {
    private final AccessTokenGeneratorProvider accessTokenGeneratorProvider;

    @Override
    public String generateAccessToken(final Account.Snapshot snapshot) {

        return this.accessTokenGeneratorProvider.generateAccessToken(
                snapshot.id().toString(),
                snapshot.username(),
                snapshot.role()
        );
    }

    public LoginAccountTokenGeneratorAdapter(AccessTokenGeneratorProvider accessTokenGeneratorProvider) {
        this.accessTokenGeneratorProvider = accessTokenGeneratorProvider;
    }
}
