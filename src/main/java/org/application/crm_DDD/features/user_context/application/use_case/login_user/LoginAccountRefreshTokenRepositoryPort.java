package org.application.crm_DDD.features.user_context.application.use_case.login_user;

import org.application.crm_DDD.features.user_context.domain.model.session.Session;

public interface LoginAccountRefreshTokenRepositoryPort {
    void save(final Session session);
}