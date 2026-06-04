package org.application.crm_DDD.features.identity_context.application.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;

import java.util.Optional;

public interface RefreshSessionRepositoryPort {
    Optional<Session> findById(final SessionId sessionId);

    void save(final Session session);
}
