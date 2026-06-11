package org.application.crm_DDD.module.identity_context.domain.repository;

import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.model.account.Username;

import java.util.Optional;

public interface AccountRepository {

    boolean existsByUsername(final Username username);

    Optional<Account> findById(final AccountId id);

    Optional<Account> findByUsername(final Username username);

    void save(final Account account);

}
