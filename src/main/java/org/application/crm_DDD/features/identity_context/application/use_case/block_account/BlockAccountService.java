package org.application.crm_DDD.features.identity_context.application.use_case.block_account;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;

import java.util.UUID;

public class BlockAccountService implements BlockAccountUseCase {
    private final BlockAccountRepositoryPort blockAccountRepository;

    @Override
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        AccountId initiatorId = new AccountId(initiatorAccountId);
        AccountId targetId = new AccountId(targetAccountId);

        Account initiatorAccount = this.blockAccountRepository
                .findById(initiatorId)
                .orElseThrow(() -> new IdentityContextDomainException("Initiator user not found, can't block", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));


        Account targetAccount = this.blockAccountRepository
                .findById(targetId)
                .orElseThrow(() -> new IdentityContextDomainException("Target user not found, can't block", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        targetAccount.blockBy(initiatorAccount);

        this.blockAccountRepository.save(targetAccount);
    }

    public BlockAccountService(BlockAccountRepositoryPort blockAccountRepository) {
        this.blockAccountRepository = blockAccountRepository;
    }
}
