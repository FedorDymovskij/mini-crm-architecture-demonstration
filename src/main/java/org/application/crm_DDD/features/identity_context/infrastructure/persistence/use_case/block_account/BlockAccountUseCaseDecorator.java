package org.application.crm_DDD.features.identity_context.infrastructure.persistence.use_case.block_account;

import org.application.crm_DDD.features.identity_context.application.use_case.block_account.BlockAccountRepositoryPort;
import org.application.crm_DDD.features.identity_context.application.use_case.block_account.BlockAccountService;
import org.application.crm_DDD.features.identity_context.application.use_case.block_account.BlockAccountUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class BlockAccountUseCaseDecorator implements BlockAccountUseCase {

    private final BlockAccountService blockAccountService;

    @Override
    @Transactional
    public void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    ) {
        this.blockAccountService.execute(
                initiatorAccountId,
                targetAccountId
        );
    }

    public BlockAccountUseCaseDecorator(
            final BlockAccountRepositoryPort blockAccountRepositoryPort
    ) {
        this.blockAccountService = new BlockAccountService(blockAccountRepositoryPort);
    }
}
