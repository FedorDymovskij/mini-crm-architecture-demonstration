package org.application.crm_DDD.features.identity_context.application.use_case.refresh_session;

import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;

import java.util.Optional;

public interface RefreshSessionAccountRepositoryPort {
    Optional<Account> findById(final AccountId accountId);
}
