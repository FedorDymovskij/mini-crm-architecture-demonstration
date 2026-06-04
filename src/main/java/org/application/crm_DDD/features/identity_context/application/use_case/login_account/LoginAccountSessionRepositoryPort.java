package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

import org.application.crm_DDD.features.identity_context.domain.model.session.Session;

public interface LoginAccountSessionRepositoryPort {
    void save(final Session session);
}