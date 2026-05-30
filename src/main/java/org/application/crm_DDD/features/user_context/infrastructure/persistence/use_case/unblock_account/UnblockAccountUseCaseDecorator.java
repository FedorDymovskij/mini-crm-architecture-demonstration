package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.unblock_account;

import org.application.crm_DDD.features.user_context.application.use_case.unblock_account.UnblockAccountCommand;
import org.application.crm_DDD.features.user_context.application.use_case.unblock_account.UnblockAccountUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UnblockAccountUseCaseDecorator implements UnblockAccountUseCase {

    private final UnblockAccountUseCase unblockAccountUseCase;

    @Transactional
    @Override
    public void execute(final UnblockAccountCommand command) {
        this.unblockAccountUseCase.execute(command);
    }

    public UnblockAccountUseCaseDecorator(UnblockAccountUseCase unblockAccountUseCase) {
        this.unblockAccountUseCase = unblockAccountUseCase;
    }
}
