package org.application.crm_DDD.module.identity_context.application.command.unblock_account;

import java.util.UUID;

public interface UnblockAccountUseCase {
    void execute(
            final UUID initiatorAccountId,
            final UUID targetAccountId
    );
}
