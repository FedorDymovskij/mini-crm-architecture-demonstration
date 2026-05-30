package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.assign_account_as_user;

import org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user.AssignAccountAsUserRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;

import java.util.Optional;

public class AssignAccountAsUserRepositoryAdapter implements AssignAccountAsUserRepositoryPort {
    private final AssignAccountAsUserRepository repository;

    @Override
    public Optional<Account> findById(final AccountId accountId) {
        return this.repository
                .findById(accountId.value())
                .map(accountEntity -> Account.fromSnapshot(
                        new Account.Snapshot(
                                accountEntity.getId(),
                                accountEntity.getUsername(),
                                accountEntity.getPassword(),
                                accountEntity.getRole(),
                                accountEntity.getStatus()
                        ))
                );
    }

    @Override
    public void save(final Account account) {
        Account.Snapshot snapshot = account.toSnapshot();

        var accountEntity = new UserContextAccountEntity(
                snapshot.id(),
                snapshot.username(),
                snapshot.password(),
                snapshot.role(),
                snapshot.status()
        );

        this.repository.save(accountEntity);
    }

    public AssignAccountAsUserRepositoryAdapter(AssignAccountAsUserRepository repository) {
        this.repository = repository;
    }
}
