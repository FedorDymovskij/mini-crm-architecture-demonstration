package org.application.crm_DDD.features.user_context.infrastructure.persistence.use_case.block_account;

import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountCommand;
import org.application.crm_DDD.features.user_context.application.use_case.block_account.BlockAccountUseCase;
import org.springframework.transaction.annotation.Transactional;

public class BlockAccountUseCaseDecorator implements BlockAccountUseCase {

    private final BlockAccountUseCase blockAccountUseCase;

    @Transactional
    @Override
    public void execute(BlockAccountCommand command) {
        this.blockAccountUseCase.execute(command);
    }

    public BlockAccountUseCaseDecorator(BlockAccountUseCase blockAccountUseCase) {
        this.blockAccountUseCase = blockAccountUseCase;
    }
}
