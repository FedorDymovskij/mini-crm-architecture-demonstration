package org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_user;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

public class AssignAccountAsUserService implements AssignAccountAsUserUseCase {
    private final AssignAccountAsUserRepositoryPort assignAccountAsUserRepository;

    @Override
    public void execute(final AssignAccountAsUserCommand command) {
        AccountId initiatorAccountId = new AccountId(command.initiatorAccountId());
        AccountId targetAccountId = new AccountId(command.targetAccountId());

        Account initiatorAccount = this.assignAccountAsUserRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new UserContextDomainException("Initiator user not found, can't assign", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        Account targetAccount = this.assignAccountAsUserRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new UserContextDomainException("Target user not found, can't assign", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        targetAccount.assignAsUserBy(initiatorAccount);

        this.assignAccountAsUserRepository.save(targetAccount);
    }

    public AssignAccountAsUserService(AssignAccountAsUserRepositoryPort assignAccountAsUserRepository) {
        this.assignAccountAsUserRepository = assignAccountAsUserRepository;
    }
}
