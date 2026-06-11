package org.application.crm_DDD.module.identity_context.application.command.block_account;

import java.util.UUID;

public interface BlockAccountUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
