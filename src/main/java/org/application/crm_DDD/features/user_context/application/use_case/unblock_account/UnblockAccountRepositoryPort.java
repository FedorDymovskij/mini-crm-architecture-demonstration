package org.application.crm_DDD.features.user_context.application.use_case.unblock_account;

import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

import java.util.Optional;

public interface UnblockAccountRepositoryPort {
    Optional<Account> findById(final AccountId accountId);

    void save(final Account account);
}
