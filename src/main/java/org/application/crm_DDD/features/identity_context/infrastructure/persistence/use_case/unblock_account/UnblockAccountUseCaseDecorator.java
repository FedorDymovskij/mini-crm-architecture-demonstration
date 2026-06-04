package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.unblock_account;

import org.application.crm_DDD.features.identity_context.application.use_case.unblock_account.UnblockAccountRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.unblock_account.UnblockAccountService;
import org.application.crm_DDD.features.identity_context.application.use_case.unblock_account.UnblockAccountUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class UnblockAccountUseCaseDecorator implements UnblockAccountUseCase {

    private final UnblockAccountService unblockAccountService;

    @Override
    @Transactional
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        this.unblockAccountService.execute(initiatorAccountId, targetAccountId);
    }

    public UnblockAccountUseCaseDecorator(
            final UnblockAccountRepositoryPort unblockAccountUseCase
    ) {
        this.unblockAccountService = new UnblockAccountService(unblockAccountUseCase);
    }
}
