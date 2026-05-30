package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.refresh_session;

import org.application.crm_DDD.features.user_context.application.use_case.refresh_session.RefreshSessionAccountRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

import java.util.Optional;

public class RefreshSessionAccountRepositoryAdapter implements RefreshSessionAccountRepositoryPort {
    private final RefreshSessionAccountRepository refreshSessionAccountRepository;

    @Override
    public Optional<Account> findById(final AccountId accountId) {
        return this.refreshSessionAccountRepository.findById(accountId.value())
                .map(accountEntity ->
                        Account.fromSnapshot(new Account.Snapshot(
                                accountEntity.getId(),
                                accountEntity.getUsername(),
                                accountEntity.getPassword(),
                                accountEntity.getRole(),
                                accountEntity.getStatus()
                        ))
                );
    }

    public RefreshSessionAccountRepositoryAdapter(RefreshSessionAccountRepository refreshSessionAccountRepository) {
        this.refreshSessionAccountRepository = refreshSessionAccountRepository;
    }
}
