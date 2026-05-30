package org.application.crm_DDD.features.user_context.application.use_case.assign_account_as_moderator;

import org.application.crm_DDD.features.user_context.domain.exception.UserContextDomainException;
import org.application.crm_DDD.features.user_context.domain.exception.UserContextExceptionReasonPhrase;
import org.application.crm_DDD.features.user_context.domain.model.account.Account;
import org.application.crm_DDD.features.user_context.domain.model.account.AccountId;

public class AssignAccountAsModeratorService implements AssignAccountAsModeratorUseCase {
    private final AssignAccountAsModeratorRepositoryPort appointModeratorRepository;

    @Override
    public void execute(final AssignAccountAsModeratorCommand command) {
        AccountId initiatorAccountId = new AccountId(command.initiatorAccountId());
        AccountId targetAccountId = new AccountId(command.targetAccountId());

        Account initiatorAccount = this.appointModeratorRepository
                .findById(initiatorAccountId)
                .orElseThrow(() -> new UserContextDomainException("Initiator user not found, can't assign", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        Account targetAccount = this.appointModeratorRepository
                .findById(targetAccountId)
                .orElseThrow(() -> new UserContextDomainException("Target user not found, can't assign", UserContextExceptionReasonPhrase.USER_NOT_FOUND));

        targetAccount.assignAsModeratorBy(initiatorAccount);

        this.appointModeratorRepository.save(targetAccount);
    }

    public AssignAccountAsModeratorService(AssignAccountAsModeratorRepositoryPort appointModeratorRepository) {
        this.appointModeratorRepository = appointModeratorRepository;
    }
}
