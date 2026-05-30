package org.application.crm_DDD.features.user_context.application.use_case.unblock_account;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

public class UnblockAccountService implements UnblockAccountUseCase {
    private final UnblockAccountRepositoryPort blockAccountRepository;

    @Override
    public void execute(final UnblockAccountCommand unblockAccountCommand) {
        AccountId initiatorAccountId = new AccountId(unblockAccountCommand.initiatorAccountId());
        AccountId targetAccountId = new AccountId(unblockAccountCommand.targetAccountId());

        Account initiatorAccount = this.blockAccountRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new UserContextDomainException("Initiator user not found, can't unblock", UserContextExceptionReasonPhrase.USER_NOT_FOUND));


        Account targetAccount = this.blockAccountRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new UserContextDomainException("Target user not found, can't unblock", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        targetAccount.unblockBy(initiatorAccount);

        this.blockAccountRepository.save(targetAccount);
    }

    public UnblockAccountService(UnblockAccountRepositoryPort blockAccountRepository) {
        this.blockAccountRepository = blockAccountRepository;
    }
}
