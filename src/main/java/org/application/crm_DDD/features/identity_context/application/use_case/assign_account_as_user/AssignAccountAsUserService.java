package org.application.crm_DDD.features.identity_context.application.use_case.assign_account_as_user;

import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;

import java.util.UUID;

public class AssignAccountAsUserService implements AssignAccountAsUserUseCase {
    private final AssignAccountAsUserRepositoryPort assignAccountAsUserRepository;

    @Override
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        AccountId initiatorId = new AccountId(initiatorAccountId);
        AccountId targetId = new AccountId(targetAccountId);

        Account initiatorAccount = this.assignAccountAsUserRepository
                .findById(initiatorId)
                .orElseThrow(() -> new IdentityContextDomainException("Initiator user not found, can't assign", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        Account targetAccount = this.assignAccountAsUserRepository
                .findById(targetId)
                .orElseThrow(() -> new IdentityContextDomainException("Target user not found, can't assign", IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        targetAccount.assignAsUserBy(initiatorAccount);

        this.assignAccountAsUserRepository.save(targetAccount);
    }

    public AssignAccountAsUserService(AssignAccountAsUserRepositoryPort assignAccountAsUserRepository) {
        this.assignAccountAsUserRepository = assignAccountAsUserRepository;
    }
}
