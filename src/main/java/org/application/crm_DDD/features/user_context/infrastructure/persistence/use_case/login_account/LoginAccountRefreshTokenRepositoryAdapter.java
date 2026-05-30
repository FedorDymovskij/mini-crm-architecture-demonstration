package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.user_context.application.use_case.login_user.LoginAccountRefreshTokenRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.session.Session;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextSessionEntity;

public class LoginAccountRefreshTokenRepositoryAdapter implements LoginAccountRefreshTokenRepositoryPort {
    private final LoginAccountRefreshTokenRepository loginAccountRefreshTokenRepository;

    @Override
    public void save(final Session session) {
        Session.Snapshot snapshot = session.toSnapshot();

        var sessionEntity = new UserContextSessionEntity(
                snapshot.id(),
                snapshot.accountId(),
                snapshot.expiration(),
                snapshot.status()
        );

        this.loginAccountRefreshTokenRepository.save(sessionEntity);
    }

    public LoginAccountRefreshTokenRepositoryAdapter(LoginAccountRefreshTokenRepository loginAccountRefreshTokenRepository) {
        this.loginAccountRefreshTokenRepository = loginAccountRefreshTokenRepository;
    }
}
