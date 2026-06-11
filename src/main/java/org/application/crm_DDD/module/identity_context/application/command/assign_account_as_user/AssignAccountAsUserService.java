package org.application.crm_DDD.module.identity_context.application.command.assign_account_as_user;

import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;

import java.util.UUID;

public class AssignAccountAsUserService implements AssignAccountAsUserUseCase {
    private final AccountRepository accountRepository;

    @Override
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        AccountId initiatorId = new AccountId(initiatorAccountId);
        AccountId targetId = new AccountId(targetAccountId);

        // Initiating
        Account initiatorAccount = this.accountRepository
                .findById(initiatorId)
                .orElseThrow(() -> new IdentityContextDomainException("Initiator user not found, can't assign", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        Account targetAccount = this.accountRepository
                .findById(targetId)
                .orElseThrow(() -> new IdentityContextDomainException("Target user not found, can't assign", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        // Assigning
        targetAccount.assignAsUserBy(initiatorAccount);

        // Saving
        this.accountRepository.save(targetAccount);
    }

    public AssignAccountAsUserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
