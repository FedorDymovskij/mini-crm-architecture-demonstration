package org.application.crm_DDD.features.identity_context.application.use_case.unblock_account;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;

import java.util.UUID;

public class UnblockAccountService implements UnblockAccountUseCase {
    private final UnblockAccountRepositoryPort blockAccountRepository;

    @Override
    public void execute(
            final UUID _initiatorAccountId,
            final UUID _targetAccountId
    ) {
        AccountId initiatorAccountId = new AccountId(_initiatorAccountId);
        AccountId targetAccountId = new AccountId(_targetAccountId);

        Account initiatorAccount = this.blockAccountRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new IdentityContextDomainException("Initiator user not found, can't unblock", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));


        Account targetAccount = this.blockAccountRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new IdentityContextDomainException("Target user not found, can't unblock", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        targetAccount.unblockBy(initiatorAccount);

        this.blockAccountRepository.save(targetAccount);
    }

    public UnblockAccountService(UnblockAccountRepositoryPort blockAccountRepository) {
        this.blockAccountRepository = blockAccountRepository;
    }
}
