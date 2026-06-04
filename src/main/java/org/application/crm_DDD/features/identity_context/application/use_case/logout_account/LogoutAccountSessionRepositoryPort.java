package org.application.crm_DDD.features.identity_context.application.use_case.logout_account;

import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;

import java.util.Optional;

public interface LogoutAccountSessionRepositoryPort {
    Optional<Session> findById(final SessionId sessionId);

    void save(final Session session);
}
