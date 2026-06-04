package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.application.use_case.refresh_session.RefreshSessionRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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

        var sessionEntity = new IdentityContextSessionEntity(
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
