package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;

import java.util.Optional;

public interface LoginAccountRepositoryPort {
    Optional<Account> findByUsername(final Username username);
}
