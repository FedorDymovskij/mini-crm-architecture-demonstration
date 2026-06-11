package org.application.crm_DDD.module.identity_context.application.command.assign_account_as_moderator;

import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.module.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.module.identity_context.domain.model.account.Account;
import org.application.crm_DDD.module.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.module.identity_context.domain.repository.AccountRepository;

import java.util.UUID;

public class AssignAccountAsModeratorService implements AssignAccountAsModeratorUseCase {
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
        targetAccount.assignAsModeratorBy(initiatorAccount);

        // Saving
        this.accountRepository.save(targetAccount);
    }

    public AssignAccountAsModeratorService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
