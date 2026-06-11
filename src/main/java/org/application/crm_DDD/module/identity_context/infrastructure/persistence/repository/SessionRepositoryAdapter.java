package org.application.crm_DDD.module.identity_context.infrastructure.persistence.repository;

import org.application.crm_DDD.module.identity_context.domain.model.session.Session;
import org.application.crm_DDD.module.identity_context.domain.model.session.SessionId;
import org.application.crm_DDD.module.identity_context.domain.repository.SessionRepository;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.entity.IdentityContextSessionEntity;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.jpa.JpaSessionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionRepositoryAdapter implements SessionRepository {
    private final JpaSessionRepository sessionRepository;

    @Override
    public Optional<Session> findById(final SessionId id) {
        return this.sessionRepository
                .findById(id.value())
                .map(sessionEntity -> Session.fromSnapshot(
                        new Session.Snapshot(
                                sessionEntity.getId(),
                                sessionEntity.getAccountId(),
                                sessionEntity.getExpiresAt(),
                                sessionEntity.getStatus()
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

        this.sessionRepository.save(sessionEntity);
    }

    public SessionRepositoryAdapter(JpaSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
