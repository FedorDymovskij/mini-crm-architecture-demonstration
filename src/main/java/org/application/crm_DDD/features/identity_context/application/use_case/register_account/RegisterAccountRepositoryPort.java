package org.application.crm_DDD.features.identity_context.application.use_case.register_account;

import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;

public interface RegisterAccountRepositoryPort {
    boolean existsByUsername(final Username username);

    void save(final Account account);
}
