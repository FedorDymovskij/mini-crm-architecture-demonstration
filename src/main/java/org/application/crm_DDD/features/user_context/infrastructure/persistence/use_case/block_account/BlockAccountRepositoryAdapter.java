package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.block_account;

import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountRepositoryPort;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.user_context.infrastructure.persistence.entity.UserContextAccountEntity;

import java.util.Optional;

public class BlockAccountRepositoryAdapter implements BlockAccountRepositoryPort {
    private final BlockAccountRepository blockAccountRepository;

    @Override
    public Optional<Account> findById(final AccountId accountId) {
        return this.blockAccountRepository.findById(accountId.value())
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

        this.blockAccountRepository.save(accountEntity);
    }

    public BlockAccountRepositoryAdapter(BlockAccountRepository blockAccountRepository) {
        this.blockAccountRepository = blockAccountRepository;
    }
}
