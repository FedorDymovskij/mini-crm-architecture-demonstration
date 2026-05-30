package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.session.Session;
import org.application.crm_DDD.features.user_context.domain.model.session.SessionId;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextSessionEntity;

import java.util.Optional;

public class RefreshSessionRepositoryAdapter implements RefreshSessionRepositoryPort {
    private final RefreshSessionRepository refreshSessionRepository;


    @Override
    public Optional<Session> findById(final SessionId sessionId) {
        return this.refreshSessionRepository.
                findById(sessionId.value())
                .map(sessionEntity -> Session.fromSnapshot(
                        new Session.Snapshot(
                                sessionEntity.getId(),
                                sessionEntity.getAccountId(),
                                sessionEntity.getExpiresAt(),
                                sessionEntity.getStatus()
                        ))
                );
    }

    @Override
    public void save(final Session session) {
        Session.Snapshot snapshot = session.toSnapshot();

        var sessionEntity = new UserContextSessionEntity(
                snapshot.id(),
                snapshot.accountId(),
                snapshot.expiration(),
                snapshot.status()
        );

        this.refreshSessionRepository.save(sessionEntity);
    }

    public RefreshSessionRepositoryAdapter(RefreshSessionRepository refreshSessionRepository) {
        this.refreshSessionRepository = refreshSessionRepository;
    }
}
