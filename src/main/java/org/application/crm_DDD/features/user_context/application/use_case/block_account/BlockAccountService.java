package org.application.crm_DDD.features.user_context.application.use_case.block_account;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

public class BlockAccountService implements BlockAccountUseCase {
    private final BlockAccountRepositoryPort blockAccountRepository;

    @Override
    public void execute(final BlockAccountCommand blockAccountCommand) {
        AccountId initiatorAccountId = new AccountId(blockAccountCommand.initiatorAccountId());
        AccountId targetAccountId = new AccountId(blockAccountCommand.targetAccountId());

        Account initiatorAccount = this.blockAccountRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new UserContextDomainException("Initiator user not found, can't block", UserContextExceptionReasonPhrase.USER_NOT_FOUND));


        Account targetAccount = this.blockAccountRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new UserContextDomainException("Target user not found, can't block", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        targetAccount.blockBy(initiatorAccount);

        this.blockAccountRepository.save(targetAccount);
    }

    public BlockAccountService(BlockAccountRepositoryPort blockAccountRepository) {
        this.blockAccountRepository = blockAccountRepository;
    }
}
