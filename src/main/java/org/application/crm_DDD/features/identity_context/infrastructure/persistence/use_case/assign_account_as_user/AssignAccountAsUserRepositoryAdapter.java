package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.assign_account_as_user;

import org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user.AssignAccountAsUserRepositoryPort;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.identity_context.infrastructure.persistence.entity.IdentityContextAccountEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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

        var accountEntity = new IdentityContextAccountEntity(
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
