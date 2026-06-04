package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.logout_account;

import org.application.crm_DDD.features.identity_context.application.use_case.logout_account.LogoutAccountSessionRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LogoutAccountSessionRepositoryAdapter implements LogoutAccountSessionRepositoryPort {
    private final LogoutAccountSessionRepository logoutAccountSessionRepository;

    @Override
    public Optional<Session> findById(final SessionId sessionId) {
        return this.logoutAccountSessionRepository
                .findById(sessionId.value())
                .map(account -> Session.fromSnapshot(
                        new Session.Snapshot(
                                account.getId(),
                                account.getAccountId(),
                                account.getExpiresAt(),
                                account.getStatus()
                        )
                ));
    }

    @Override
    public void save(final Session session) {
        Session.Snapshot snapshot = session.toSnapshot();

        var sessionEntity = new IdentityContextSessionEntity(
                snapshot.id(),
                snapshot.accountId(),
                snapshot.expiration(),
                snapshot.status()
        );

        this.logoutAccountSessionRepository.save(sessionEntity);
    }

    public LogoutAccountSessionRepositoryAdapter(LogoutAccountSessionRepository logoutAccountSessionRepository) {
        this.logoutAccountSessionRepository = logoutAccountSessionRepository;
    }
}
