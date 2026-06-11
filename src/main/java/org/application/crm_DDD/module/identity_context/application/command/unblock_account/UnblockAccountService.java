package org.application.crm_DDD.module.identity_context.application.command.unblock_account;

import jakarta.transaction.Transactional;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;

import java.util.UUID;

public class UnblockAccountService implements UnblockAccountUseCase {
    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public void execute(
            final UUID _initiatorAccountId,
            final UUID _targetAccountId
    ) {
        AccountId initiatorAccountId = new AccountId(_initiatorAccountId);
        AccountId targetAccountId = new AccountId(_targetAccountId);

        // Initiating
        Account initiatorAccount = this.accountRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new IdentityContextDomainException("Initiator user not found, can't unblock", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));


        Account targetAccount = this.accountRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new IdentityContextDomainException("Target user not found, can't unblock", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        // Unblocking
        targetAccount.unblockBy(initiatorAccount);

        // Saving
        this.accountRepository.save(targetAccount);
    }

    public UnblockAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
