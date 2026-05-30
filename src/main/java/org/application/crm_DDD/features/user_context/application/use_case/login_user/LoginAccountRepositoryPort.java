package org.application.crm_DDD.features.user_context.application.use_case.login_user;

import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.Username;

import java.util.Optional;

public interface LoginAccountRepositoryPort {
    Optional<Account> findByUsername(final Username username);
}
