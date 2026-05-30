package org.application.crm_DDD.features.user_context.application.use_case.register_user;

import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.Username;

public interface RegisterUserRepositoryPort {
    boolean existsByUsername(final Username username);

    void save(final Account account);
}
