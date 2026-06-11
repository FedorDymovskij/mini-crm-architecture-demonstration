package org.application.crm_DDD.module.identity_context.infrastructure.persistence.repository;

import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.model.account.Username;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.entity.IdentityContextAccountEntity;
import org.application.crm_DDD.module.identity_context.infrastructure.persistence.jpa.JpaAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryAdapter implements AccountRepository {
    private final JpaAccountRepository jpaAccountRepository;

    @Override
    public boolean existsByUsername(final Username username) {
        return this.jpaAccountRepository.existsByUsername(username.value());
    }

    @Override
    public Optional<Account> findById(final AccountId id) {
        return this.jpaAccountRepository
                .findById(id.value())
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
    public Optional<Account> findByUsername(final Username username) {
        return this.jpaAccountRepository
                .findByUsername(username.value())
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
    public void save(Account account) {
        Account.Snapshot snapshot = account.toSnapshot();

        var accountEntity = new IdentityContextAccountEntity(
                snapshot.id(),
                snapshot.username(),
                snapshot.password(),
                snapshot.role(),
                snapshot.status()
        );

        this.jpaAccountRepository.save(accountEntity);
    }

    public AccountRepositoryAdapter(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }
}
