package org.application.crm_DDD.module.identity_context.domain.repository;

import org.application.crm_DDD.module.identity_context.domain.model.session.Session;
import org.application.crm_DDD.module.identity_context.domain.model.session.SessionId;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(final SessionId id);

    void save(final Session session);
}
