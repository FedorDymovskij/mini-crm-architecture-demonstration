package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.login_account;

import org.application.crm_DDD.features.identity_context.application.use_case.login_account.LoginAccountSessionRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginAccountRefreshTokenRepositoryAdapter implements LoginAccountSessionRepositoryPort {
    private final LoginAccountSessionRepository loginAccountRefreshTokenRepository;

    @Override
    public void save(final Session session) {
        Session.Snapshot snapshot = session.toSnapshot();

        var sessionEntity = new IdentityContextSessionEntity(
                snapshot.id(),
                snapshot.accountId(),
                snapshot.expiration(),
                snapshot.status()
        );

        this.loginAccountRefreshTokenRepository.save(sessionEntity);
    }

    public LoginAccountRefreshTokenRepositoryAdapter(LoginAccountSessionRepository loginAccountRefreshTokenRepository) {
        this.loginAccountRefreshTokenRepository = loginAccountRefreshTokenRepository;
    }
}
